/*
 * Created by Daniel Marell 14-09-20 00:39
 */
package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.InvalidPropertyException;

/**
 * Represents a runtime environment, like development, test, staging, production.
 * The environment is set using an system property spring-cd-environment.
 * Possible values are:
 * LOCAL for local development (default if missing)
 * AUTOSMALL for small/fast automatic tests
 * AUTOLARGE for large/slow automatic tests
 * MAN for Manuel test environment
 * PROD for Production environment
 */
public enum Environment {
    LOCAL, AUTOSMALL, AUTOLARGE, MAN, PROD;

    private static Logger logger = LoggerFactory.getLogger(Environment.class);

    private static final String ENVIRONMENT_PROPERTY = "spring-cd.environment";

    public static Environment getCurrentEnvironment() {
        return getEnvironment(System.getProperty(ENVIRONMENT_PROPERTY));
    }

    public static void setEnvironment(Environment e) {
        System.setProperty(ENVIRONMENT_PROPERTY, e.name());
    }

    private static Environment getEnvironment(String environmentParam) {
        Environment result;
        if (environmentParam != null) {
            try {
                result = Environment.valueOf(environmentParam.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new InvalidPropertyException(Environment.class, environmentParam,
                        "Unable to detect environment based property " + ENVIRONMENT_PROPERTY + ":" + e.getMessage());
            }
        } else {
            result = LOCAL;
        }
        logger.info("environment=" + result.name());
        return result;
    }
}
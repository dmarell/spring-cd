/*
 * Created by Daniel Marell 14-09-20 01:04
 */
package hello;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Config {
    private int httpPort;
    private String backgroundColor;

    @PostConstruct
    private void init() {
        switch (Environment.getCurrentEnvironment()) {
            case AUTOSMALL:
                httpPort = 8080;
                backgroundColor = "red";
                break;
            case AUTOLARGE:
                httpPort = 8080;
                backgroundColor = "red";
                break;
            case MAN:
                httpPort = 8080;
                backgroundColor = "magenta";
                break;
            case PROD:
                httpPort = 8080;
                backgroundColor = "white";
                break;
            case LOCAL:
            default:
                httpPort = 8080;
                backgroundColor = "blue";
                break;
        }

    }

    public int getHttpPort() {
        return httpPort;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }
}

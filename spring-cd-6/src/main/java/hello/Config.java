/*
 * Created by Daniel Marell 14-09-20 01:04
 */
package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Config {
    private String backgroundColor;

    @Autowired
    private Environment environment;

    @PostConstruct
    private void init() {
        backgroundColor = environment.getProperty("hello.backgroundcolor");
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }
}

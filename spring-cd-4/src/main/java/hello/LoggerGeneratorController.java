/*
 * Created by Daniel Marell 14-10-20 21:54
 */
package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerGeneratorController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/generateloggings", method = RequestMethod.GET)
    public void generateLoggings() {
        for (int i = 0; i < 74; ++i) {
            logger.info("author=Homer,content=Text " + i);
        }
        for (int i = 0; i < 48; ++i) {
            logger.info("author=Lisa,content=Text " + i);
        }
        for (int i = 0; i < 37; ++i) {
            logger.info("author=Marge,content=Text " + i);
        }
        for (int i = 0; i < 24; ++i) {
            logger.info("author=Bart,content=Text " + i);
        }
        for (int i = 0; i < 14; ++i) {
            logger.info("author=Flanders,content=Text " + i);
        }
        for (int i = 0; i < 5; ++i) {
            logger.info("author=Milhouse,content=Text " + i);
        }
    }
}

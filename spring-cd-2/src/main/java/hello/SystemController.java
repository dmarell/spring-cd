/*
 * Created by Daniel Marell 14-03-02 12:02
 */
package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {
    @RequestMapping(value = "/appversion", method = RequestMethod.GET)
    public String getAppVersion() {
        return BuildInfo.getAppVersion();
    }
}
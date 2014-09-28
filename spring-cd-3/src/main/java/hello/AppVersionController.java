/*
 * Created by Daniel Marell 14-03-02 12:02
 */
package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppVersionController {
    @RequestMapping(value = "/appversion", method = RequestMethod.GET)
    @ResponseBody
    public String getAppVersion() {
        return BuildInfo.getAppVersion();
    }
}
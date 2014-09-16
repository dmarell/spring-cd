/*
 * Created by Daniel Marell 14-03-02 12:02
 */
package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VersionController {
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    @ResponseBody
    public String getVersion() {
        return BuildInfo.getApplicationVersion();
    }
}
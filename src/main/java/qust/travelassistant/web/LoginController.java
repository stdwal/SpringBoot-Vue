package qust.travelassistant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qust.travelassistant.exception.TravelAssistantException;
import qust.travelassistant.service.LoginService;

@RestController
@RequestMapping("/travel-assistant/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String signIn(String userName, String password) {
        try {
            loginService.signIn(userName, password);
            return "OK";
        } catch (TravelAssistantException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(String userName, String password, String confirmPassword) {
        try {
            loginService.signUp(userName, password, confirmPassword);
            return "OK";
        } catch (TravelAssistantException e) {
            return e.getMessage();
        }
    }


}

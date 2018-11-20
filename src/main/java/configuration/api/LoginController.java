package configuration.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class LoginController {

    @RequestMapping(value = Routes.LOGIN_ROUTE, method = GET)
    public void login() {

    }
}

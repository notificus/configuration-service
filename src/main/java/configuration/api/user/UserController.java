package configuration.api.user;

import configuration.api.Routes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class UserController {
    @RequestMapping(value = Routes.USERS_ROUTE, method = GET)
    public String listUsers() {
        return "Here is a list of users";
    }

    @RequestMapping(value = Routes.USER_ROUTE, method = GET)
    public String getUser(@PathVariable String cip) {
        return cip;
    }

//    public String index() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && auth.getPrincipal() != null && auth.getPrincipal() instanceof UserDetails) {
//            return ((UserDetails) auth.getPrincipal()).getUsername();
//        }
//        return "Greetings";
//    }
}

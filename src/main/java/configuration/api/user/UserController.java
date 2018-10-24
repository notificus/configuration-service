package configuration.api.user;

import configuration.api.Routes;
import configuration.service.user.UserService;
import configuration.service.user.persistent.PersistentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class UserController {
    private final String CIP_PLACEHOLDER = "me";
    @Autowired
    UserService userService;

    @RequestMapping(value = Routes.USERS_ROUTE, method = GET)
    public List<UserContract> listUsers() {
        return UserContractTranslator.translateTo(userService.listUsers());
    }

    @RequestMapping(value = Routes.USER_ROUTE, method = GET)
    public UserContract getUser(@PathVariable String cip) {
        if (cip.equals(CIP_PLACEHOLDER)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getPrincipal() != null && auth.getPrincipal() instanceof UserDetails) {
                cip = ((UserDetails) auth.getPrincipal()).getUsername();
            }
        }

        return UserContractTranslator.translateTo(userService.getUser(cip));
    }

    @RequestMapping(value = Routes.USER_ROUTE, method = PUT)
    public UserContract updateUser(@PathVariable String cip, @RequestBody UserContract userContract) {
        return UserContractTranslator.translateTo(userService.updateUser(cip, UserContractTranslator.translateFrom(userContract)));
    }

//    public String index() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && auth.getPrincipal() != null && auth.getPrincipal() instanceof UserDetails) {
//            return ((UserDetails) auth.getPrincipal()).getUsername();
//        }
//        return "Greetings";
//    }
}

package configuration.api.user;

import configuration.api.Routes;
import configuration.service.user.UserService;
import configuration.service.user.persistent.PersistentUserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserContract>> listUsers() {
        return new ResponseEntity<>(UserContractTranslator.translateTo(userService.listUsers()), HttpStatus.OK);
    }

    @RequestMapping(value = Routes.USER_ROUTE, method = GET)
    public ResponseEntity<UserContract> getUser(@PathVariable String cip) {
        if (cip.equals(CIP_PLACEHOLDER)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getPrincipal() != null && auth.getPrincipal() instanceof UserDetails) {
                cip = ((UserDetails) auth.getPrincipal()).getUsername();
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        return new ResponseEntity<>(UserContractTranslator.translateTo(userService.getUser(cip)), HttpStatus.OK);
    }

    @RequestMapping(value = Routes.USER_ROUTE, method = PUT)
    public ResponseEntity<UserContract> updateUser(@PathVariable String cip, @RequestBody UserContract userContract) {
        return new ResponseEntity<>(UserContractTranslator.translateTo(
                userService.updateUser(cip, UserContractTranslator.translateFrom(userContract))), HttpStatus.OK);
    }
}

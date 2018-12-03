package configuration.api.user;

import configuration.api.Routes;
import configuration.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = Routes.USERS_ROUTE, method = GET)
    public ResponseEntity<List<UserContract>> listUsers() {
        return new ResponseEntity<>(UserContractTranslator.translateTo(userService.listUsers()), HttpStatus.OK);
    }

    @RequestMapping(value = Routes.USERS_ROUTE, method = POST)
    public ResponseEntity<UserContract> createUser(@RequestBody UserContract userContract) {
        return new ResponseEntity<>(UserContractTranslator.translateTo(
                userService.createUser(UserContractTranslator.translateFrom(userContract))
        ), HttpStatus.OK);
    }

    @RequestMapping(value = Routes.USER_ROUTE, method = GET)
    public ResponseEntity<UserContract> getUser(@PathVariable String cip) {
        return new ResponseEntity<>(UserContractTranslator.translateTo(userService.getUser(cip)), HttpStatus.OK);
    }

    @RequestMapping(value = Routes.USER_ROUTE, method = PUT)
    public ResponseEntity<UserContract> updateUser(@PathVariable String cip, @RequestBody UserContract userContract) {
        return new ResponseEntity<>(UserContractTranslator.translateTo(
                userService.updateUser(cip, UserContractTranslator.translateFrom(userContract))), HttpStatus.OK);
    }
}

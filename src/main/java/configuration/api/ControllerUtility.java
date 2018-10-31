package configuration.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.server.ResponseStatusException;

public class ControllerUtility {
    public static final String CIP_PLACEHOLDER = "me";

    public static String getCurrentUser(String cip) {
        if (cip.equals(CIP_PLACEHOLDER)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth != null && auth.getPrincipal() != null && auth.getPrincipal() instanceof UserDetails) {
                return ((UserDetails) auth.getPrincipal()).getUsername();
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not currently logged in");
            }
        } else {
            return cip;
        }
    }
}

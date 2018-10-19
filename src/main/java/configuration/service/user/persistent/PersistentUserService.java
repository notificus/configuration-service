package configuration.service.user.persistent;

import configuration.service.user.User;
import configuration.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class PersistentUserService implements UserDetailsService, UserService {
    @Override
    public UserDetails loadUserByUsername(String cip) throws UsernameNotFoundException {
        try {
            return getUser(cip);
        } catch (Exception e) {
            createUser(User.builder().withCip(cip).build());
            return null;
        }
    }

    @Override
    public List<User> listUsers() {
        return emptyList();
    }

    @Override
    public User getUser(String cip) {
        return User.builder().withCip(cip).build();
    }

    @Override
    public User createUser(User user) {
        return user;
    }

    @Override
    public User updateUser(User user) {
        return user;
    }
}

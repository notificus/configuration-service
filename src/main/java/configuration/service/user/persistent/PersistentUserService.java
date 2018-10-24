package configuration.service.user.persistent;

import configuration.service.user.User;
import configuration.service.user.UserService;
import configuration.service.user.exception.CipMismatchException;
import configuration.service.user.persistent.postgresql.PostgresqlUserRepository;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class PersistentUserService implements UserDetailsService, UserService {
    private final String PERSONAL_CIP = "me";
    @Autowired
    PostgresqlUserRepository postgresqlUserRepository;

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
        return UserEntityTranslator.translateFrom(postgresqlUserRepository.listUsers());
    }

    @Override
    public User getUser(String cip) {
        return UserEntityTranslator.translateFrom(postgresqlUserRepository.getUser(cip));
    }

    @Override
    public User createUser(User user) {
        return UserEntityTranslator.translateFrom(postgresqlUserRepository.createUser(UserEntityTranslator.translateTo(user)));
    }

    @Override
    public User updateUser(String cip, User user) {
        if (cip.equals(user.getCip())) {
            return UserEntityTranslator.translateFrom(postgresqlUserRepository.updateUser(UserEntityTranslator.translateTo(user)));
        } else {
            throw new CipMismatchException();
        }
    }
}

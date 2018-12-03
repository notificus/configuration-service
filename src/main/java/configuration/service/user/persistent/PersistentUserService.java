package configuration.service.user.persistent;

import configuration.service.user.User;
import configuration.service.user.UserService;
import configuration.service.user.exception.CipMismatchException;
import configuration.service.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersistentUserService implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> listUsers() {
        return UserEntityTranslator.translateFrom(userRepository.findAll());
    }

    @Override
    public User getUser(String cip) {
        Optional<UserEntity> userEntity = userRepository.findById(cip);
        if (userEntity.isPresent()) {
            return UserEntityTranslator.translateFrom(userEntity.get());
        } else {
            throw new UserNotFoundException(cip);
        }
    }

    @Override
    public User createUser(User user) {
        return UserEntityTranslator.translateFrom(userRepository.save(UserEntityTranslator.translateTo(user)));
    }

    @Override
    public User updateUser(String cip, User user) {
        if (cip.equals(user.getCip())) {
            return UserEntityTranslator.translateFrom(userRepository.save(UserEntityTranslator.translateTo(user)));
        } else {
            throw new CipMismatchException();
        }
    }
}

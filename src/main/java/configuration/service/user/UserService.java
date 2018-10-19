package configuration.service.user;

import java.util.List;

public interface UserService {
    List<User> listUsers();
    User getUser(String cip);
    User createUser(User user);
    User updateUser(User user);
}

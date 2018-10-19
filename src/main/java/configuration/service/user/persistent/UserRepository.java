package configuration.service.user.persistent;

import configuration.service.user.User;

import java.util.List;

public interface UserRepository {
    List<UserEntity> listUsers();
    UserEntity getUser(String cip);
    UserEntity createUser(User user);
    UserEntity updateUser(User user);
}

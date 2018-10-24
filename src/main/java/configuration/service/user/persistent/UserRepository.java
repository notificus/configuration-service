package configuration.service.user.persistent;

import java.util.List;

public interface UserRepository {
    List<UserEntity> listUsers();
    UserEntity getUser(String cip);
    UserEntity createUser(UserEntity user);
    UserEntity updateUser(UserEntity user);
}

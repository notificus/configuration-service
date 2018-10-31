package configuration.service.user.persistent;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, String> {
//    List<UserEntity> listUsers();
//    UserEntity getUser(String cip);
//    UserEntity createUser(UserEntity user);
//    UserEntity updateUser(UserEntity user);
}

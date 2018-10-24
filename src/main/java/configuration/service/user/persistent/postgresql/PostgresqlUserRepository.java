package configuration.service.user.persistent.postgresql;

import configuration.service.user.User;
import configuration.service.user.persistent.UserEntity;
import configuration.service.user.persistent.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PostgresqlUserRepository implements UserRepository {
    @Override
    public List<UserEntity> listUsers() {
        return new ArrayList<UserEntity>(Arrays.asList(UserEntity.builder().build()));
    }

    @Override
    public UserEntity getUser(String cip) {
        return UserEntity.builder().withCip(cip).withFirstName("Phil").withLastName("Garneau").build();
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userEntity;
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return userEntity;
    }
}

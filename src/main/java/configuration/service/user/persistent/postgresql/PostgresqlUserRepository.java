package configuration.service.user.persistent.postgresql;

import configuration.service.user.User;
import configuration.service.user.persistent.UserEntity;
import configuration.service.user.persistent.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgresqlUserRepository implements UserRepository {
    @Override
    public List<UserEntity> listUsers() {
        return null;
    }

    @Override
    public UserEntity getUser(String cip) {
        return null;
    }

    @Override
    public UserEntity createUser(User user) {
        return null;
    }

    @Override
    public UserEntity updateUser(User user) {
        return null;
    }
}

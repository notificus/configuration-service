package configuration.service.user.persistent;

import configuration.service.user.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserEntityTranslator {
    public static UserEntity translateTo(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setCip(user.getCip());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        return userEntity;
    }

    public static List<UserEntity> translateTo(List<User> users) {
        return users.stream().map(user -> translateTo(user)).collect(Collectors.toList());
    }

    public static User translateFrom(UserEntity userEntity) {
        return User.builder()
                .withCip(userEntity.getCip())
                .withFirstName(userEntity.getFirstName())
                .withLastName(userEntity.getLastName())
                .build();
    }

    public static List<User> translateFrom(Iterable<UserEntity> userEntities) {
        return StreamSupport.stream(userEntities.spliterator(), false).map(userEntity -> translateFrom(userEntity)).collect(Collectors.toList());
    }
}

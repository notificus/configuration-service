package configuration.service.user.persistent;

import configuration.service.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserEntityTranslator {
    public static UserEntity translateTo(User user) {
        return UserEntity.builder()
                .withCip(user.getCip())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .build();
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

    public static List<User> translateFrom(List<UserEntity> userEntities) {
        return userEntities.stream().map(userEntity -> translateFrom(userEntity)).collect(Collectors.toList());
    }
}

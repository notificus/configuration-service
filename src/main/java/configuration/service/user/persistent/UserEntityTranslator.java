package configuration.service.user.persistent;

import configuration.service.user.User;

public class UserEntityTranslator {
    public static UserEntity translateTo(User user) {
        return UserEntity.builder()
                .withCip(user.getCip())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .build();
    }

    public static User translateFrom(UserEntity userEntity) {
        return User.builder()
                .withCip(userEntity.getCip())
                .withFirstName(userEntity.getFirstName())
                .withLastName(userEntity.getLastName())
                .build();
    }
}

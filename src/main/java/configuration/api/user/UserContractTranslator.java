package configuration.api.user;

import configuration.service.user.User;

public class UserContractTranslator {
    public static UserContract translateTo(User user) {
        return UserContract.builder()
                .withCip(user.getCip())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .build();
    }

    public static User translateFrom(UserContract userContract) {
        return User.builder()
                .withCip(userContract.getCip())
                .withFirstName(userContract.getFirstName())
                .withLastName(userContract.getLastName())
                .build();
    }
}

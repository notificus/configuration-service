package configuration.api.user;

import configuration.service.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserContractTranslator {
    public static UserContract translateTo(User user) {
        return UserContract.builder()
                .withCip(user.getCip())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .build();
    }

    public static List<UserContract> translateTo(List<User> users) {
        return users.stream().map(user -> translateTo(user)).collect(Collectors.toList());
    }

    public static User translateFrom(UserContract userContract) {
        return User.builder()
                .withCip(userContract.getCip())
                .withFirstName(userContract.getFirstName())
                .withLastName(userContract.getLastName())
                .build();
    }

    public static List<User> translateFrom(List<UserContract> userContracts) {
        return userContracts.stream().map(userContract -> translateFrom(userContract)).collect(Collectors.toList());
    }
}

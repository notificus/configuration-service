package service;

import configuration.service.user.User;
import configuration.service.user.exception.CipMismatchException;
import configuration.service.user.exception.UserNotFoundException;
import configuration.service.user.persistent.PersistentUserService;
import configuration.service.user.persistent.UserEntity;
import configuration.service.user.persistent.UserEntityTranslator;
import configuration.service.user.persistent.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
public class TestUserService {
    @TestConfiguration
    static class PersistentUserServiceTestContextConfiguration {
        @Bean
        public PersistentUserService userService() {
            return new PersistentUserService();
        }
    }

    @Autowired
    private PersistentUserService userService;

    @MockBean
    private UserRepository userRepository;

    private String cip;
    private UserEntity userEntity;
    private User expectedUser;

    @Before
    public void setUp() {
        cip = "test1111";
        userEntity = new UserEntity();
        userEntity.setCip(cip);
        userEntity.setFirstName("Test");
        userEntity.setLastName("Testing");

        expectedUser = User.builder()
                .withCip(cip)
                .withFirstName(userEntity.getFirstName())
                .withLastName(userEntity.getLastName())
                .build();
    }

    @Test
    public void given_cip_when_getUser_then_returns_user() {
        // Given
        Mockito.when(userRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(userEntity));

        // When
        User user = userService.getUser(cip);

        // Then
        assertThat(user.getCip()).isEqualTo(user.getCip());
        assertThat(user.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(user.getLastName()).isEqualTo(user.getLastName());
    }

    @Test(expected = UserNotFoundException.class)
    public void given_wrong_cip_when_getUser_then_throws_UserNotFoundException() {
        // Given
        given(userRepository.findById(anyString())).willReturn(Optional.empty());

        // When
        User user = userService.getUser(cip);
    }

    @Test
    public void when_listUsers_then_return_all_users() {
        // Given
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setCip("test2222");
        userEntity2.setFirstName("Test2");
        userEntity2.setLastName("Testing2");

        List<UserEntity> userEntities = Arrays.asList(userEntity, userEntity2);

        Mockito.when(userRepository.findAll()).thenReturn(userEntities);

        User expectedUser2 = User.builder()
                .withCip("test2222")
                .withFirstName("Test2")
                .withLastName("Testing2")
                .build();

        List<User> expectedUsers = Arrays.asList(expectedUser, expectedUser2);

        // When
        List<User> users = userService.listUsers();

        // Then
        assertThat(users.size()).isEqualTo(expectedUsers.size());

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            User expectedUser = expectedUsers.get(i);

            assertThat(user.getCip()).isEqualTo(expectedUser.getCip());
            assertThat(user.getFirstName()).isEqualTo(expectedUser.getFirstName());
            assertThat(user.getLastName()).isEqualTo(expectedUser.getLastName());
        }
    }

    @Test
    public void given_user_and_cip_that_matches_when_updateUser_then_returns_updated_user() {
        // Given
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        // When
        User user = userService.updateUser(cip, UserEntityTranslator.translateFrom(userEntity));

        // Then
        assertThat(user.getCip()).isEqualTo(expectedUser.getCip());
        assertThat(user.getFirstName()).isEqualTo(expectedUser.getFirstName());
        assertThat(user.getLastName()).isEqualTo(expectedUser.getLastName());
    }

    @Test(expected = CipMismatchException.class)
    public void given_user_and_cip_that_mismatches_when_updateUser_then_throws_exception() {
        // Given
        UserEntity userEntity = new UserEntity();
        userEntity.setCip("test1112");

        // When
        User user = userService.updateUser(cip, UserEntityTranslator.translateFrom(userEntity));
    }

    @Test
    public void given_user_when_createUser_then_returns_newUser() {
        // Given
        given(userRepository.save(any())).willReturn(userEntity);

        // When
        User user = userService.createUser(expectedUser);

        // Then
        assertThat(user.getCip()).isEqualTo(expectedUser.getCip());
        assertThat(user.getFirstName()).isEqualTo(expectedUser.getFirstName());
        assertThat(user.getLastName()).isEqualTo(expectedUser.getLastName());
    }

    @Test
    public void given_cip_when_deleteUser() {
        doNothing().when(userRepository).deleteById(cip);
    }
}

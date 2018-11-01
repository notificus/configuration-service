package service;

import configuration.service.user.User;
import configuration.service.user.UserService;
import configuration.service.user.exception.CipMismatchException;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

@RunWith(SpringRunner.class)
public class TestUserService {

    @TestConfiguration
    static class PersistentUserServiceTestContextConfiguration{
        @Bean
        public PersistentUserService userService(){
            return new PersistentUserService();
        }
    }

    @Autowired
    private PersistentUserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void given_cip_than_getUser_return_user(){
        String cip = "test1111";
        String firstName = "Test";
        String lastName = "Testing";

        UserEntity userEntity = new UserEntity();
        userEntity.setCip(cip);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);

        Mockito.when(userRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(userEntity));

        User expectedUser = User.builder().withCip(cip).withFirstName(firstName).withLastName(lastName).build();

        User user = userService.getUser(cip);

        assertThat(user.getCip()).isEqualTo(expectedUser.getCip());
        assertThat(user.getFirstName()).isEqualTo(expectedUser.getFirstName());
        assertThat(user.getLastName()).isEqualTo(expectedUser.getLastName());
    }

    @Test
    public void given_cip_and_user_exists_than_loadUserByUsername_return_user(){
        String cip = "test1111";
        String firstName = "Test";
        String lastName = "Testing";

        UserEntity userEntity = new UserEntity();
        userEntity.setCip(cip);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);

        Mockito.when(userRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(userEntity));

        User expectedUser = User.builder().withCip(cip).withFirstName(firstName).withLastName(lastName).build();

        UserDetails user = userService.loadUserByUsername(cip);

        assertThat(((User)user).getCip()).isEqualTo(expectedUser.getCip());
        assertThat(((User)user).getFirstName()).isEqualTo(expectedUser.getFirstName());
        assertThat(((User)user).getLastName()).isEqualTo(expectedUser.getLastName());
    }

    @Test
    public void given_cip_and_user_does_not_exist_than_loadUserByUsername_return_new_user(){
        String cip = "test1111";
        String firstName = "Test";
        String lastName = "Testing";

        UserEntity userEntity = new UserEntity();
        userEntity.setCip(cip);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);

        Mockito.when(userRepository.findById(Mockito.any(String.class))).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        User expectedUser = User.builder().withCip(cip).withFirstName(firstName).withLastName(lastName).build();

        UserDetails user = userService.loadUserByUsername(cip);

        assertThat(((User)user).getCip()).isEqualTo(expectedUser.getCip());
        assertThat(((User)user).getFirstName()).isEqualTo(expectedUser.getFirstName());
        assertThat(((User)user).getLastName()).isEqualTo(expectedUser.getLastName());
    }

    @Test
    public void listUsers_return_all_users(){
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setCip("test1111");
        userEntity1.setFirstName("Test1");
        userEntity1.setLastName("Testing1");

        UserEntity userEntity2= new UserEntity();
        userEntity2.setCip("test2222");
        userEntity2.setFirstName("Test2");
        userEntity2.setLastName("Testing2");

        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(userEntity1);
        userEntities.add(userEntity2);

        Mockito.when(userRepository.findAll()).thenReturn(userEntities);

        User expectedUser1 = User.builder().withCip("test1111").withFirstName("Test1").withLastName("Testing1").build();
        User expectedUser2 = User.builder().withCip("test2222").withFirstName("Test2").withLastName("Testing2").build();

        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(expectedUser1);
        expectedUsers.add(expectedUser2);

        List<User> users = userService.listUsers();

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
    public void given_user_and_cip_that_matches_than_updateUser_return_updated_user(){
        String cip = "test1111";
        String firstName = "Test";
        String lastName = "Testing";

        UserEntity userEntity = new UserEntity();
        userEntity.setCip(cip);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);

        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        User expectedUser = User.builder().withCip(cip).withFirstName(firstName).withLastName(lastName).build();

        User user = userService.updateUser(cip, UserEntityTranslator.translateFrom(userEntity));

        assertThat(user.getCip()).isEqualTo(expectedUser.getCip());
        assertThat(user.getFirstName()).isEqualTo(expectedUser.getFirstName());
        assertThat(user.getLastName()).isEqualTo(expectedUser.getLastName());
    }

    @Test(expected = CipMismatchException.class)
    public void given_user_and_cip_that_mismatches_than_updateUser_throws_exception(){
        String cip = "test1111";

        UserEntity userEntity = new UserEntity();
        userEntity.setCip("test1112");

        User user = userService.updateUser(cip, UserEntityTranslator.translateFrom(userEntity));
    }
}

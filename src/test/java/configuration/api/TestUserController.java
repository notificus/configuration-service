package configuration.api;

import configuration.Application;
import configuration.api.user.UserController;
import configuration.service.user.User;
import configuration.service.user.UserService;
import configuration.service.user.persistent.PersistentUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class, loader = AnnotationConfigContextLoader.class)
@WebMvcTest(UserController.class)
public class TestUserController {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersistentUserService userService;

    @Test
    public void listUsers_whenSuccessfulRequest_ReturnsOK() throws Exception {
        // Given
        List<User> users = Arrays.asList(User.builder().withCip("test_cip").build());
        given(userService.listUsers()).willReturn(users);

        // When
        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
//                .andExpect(jsonPath("$[0].cip", is("test_cip")));

    }

    @Test
    public void getUser_whenSuccessfulRequest_ReturnsUser() throws Exception {
        // Given
        User expectedUser = User.builder().withCip("garp2405").withFirstName("phil").withLastName("garneau").build();
        given(userService.getUser("garp2405")).willReturn(expectedUser);

        // When
        mvc.perform(get("/users/garp2405")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

}

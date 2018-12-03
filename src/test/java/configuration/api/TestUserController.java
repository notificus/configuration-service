package configuration.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.api.user.UserController;
import configuration.service.user.User;
import configuration.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class TestUserController {
    @MockBean
    private UserService userService;

    private MockMvc mvc;
    private User user;
    private String userJson;
    private String cip;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(new UserController(userService))
                .build();
        cip = "test1111";
        user = User.builder().withCip(cip).withFirstName("Test").withLastName("Testing").build();
        userJson = "{\"cip\":\""
                + user.getCip() +
                "\",\"firstName\":\"" +
                user.getFirstName() +
                "\",\"lastName\":\"" +
                user.getLastName() +
                "\"}";
    }

    @Test
    public void listUsers_whenSuccessfulRequest_ReturnsOK_And_Users() throws Exception {
        // Given
        String expectedJson = "[" + userJson + "]";
        List<User> users = Arrays.asList(user);
        given(userService.listUsers()).willReturn(users);

        // When
        MvcResult result = mvc.perform(get(Routes.USERS_ROUTE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andReturn();

        String json = result.getResponse().getContentAsString();
        assertThat(json).isEqualTo(expectedJson);
    }

    @Test
    public void createUser_whenSuccessfulRequest_ReturnsOK_and_NewUser() throws Exception {
        // Given
        String userJson = new ObjectMapper().writeValueAsString(user);
        given(userService.createUser(Mockito.any(User.class))).willReturn(user);

        // When
        MvcResult result = mvc.perform(post(Routes.USERS_ROUTE)
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        assertThat(json).isEqualTo(userJson);
    }

    @Test
    public void getUser_whenSuccessfulRequest_ReturnsOK_And_User() throws Exception {
        // Given
        given(userService.getUser(cip)).willReturn(user);

        // When
        MvcResult result = mvc.perform(get(Routes.USER_ROUTE, cip)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        assertThat(json).isEqualTo(userJson);
    }

    @Test
    public void updateUser_whenSuccessfulRequest_ReturnsOK_And_UpdatedUser() throws Exception {
        // Given
        String userJson = new ObjectMapper().writeValueAsString(user);
        given(userService.updateUser(Mockito.anyString(), Mockito.any(User.class))).willReturn(user);

        // When
        MvcResult result = mvc.perform(put(Routes.USER_ROUTE, cip)
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        assertThat(json).isEqualTo(userJson);
    }
}

import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.Application;
import configuration.api.Routes;
import configuration.api.configuration.ConfigurationContract;
import configuration.api.user.UserContract;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@AutoConfigureMockMvc
public class IntegrationTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void createNewUser_then_getNewUser_and_newUserConfiguration_then_updateConfiguration_then_deleteUser() throws Exception {
        // Given new user information
        String newUserCip = "test1111";
        ObjectMapper objectMapper = new ObjectMapper();

        UserContract newUser = UserContract.builder()
                .withCip(newUserCip)
                .withFirstName("Tester")
                .withLastName("Testing").build();
        String newUserJson = objectMapper.writeValueAsString(newUser);

        ConfigurationContract newUserConfiguration = ConfigurationContract.builder()
                .withEmailEnabled(true)
                .withEmails(Arrays.asList(newUserCip + "@usherbrooke.ca"))
                .build();
        String newUserConfigurationJson = objectMapper.writeValueAsString(newUserConfiguration);

        // When creating new user
        MvcResult result = mvc.perform(post(Routes.USERS_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUserJson))
                .andExpect(status().isOk())
                .andReturn();

        // Then new user information sent and received are equal
        String returnedJson = result.getResponse().getContentAsString();
        assertThat(returnedJson).isEqualTo(newUserJson);

        // When getting new user
        result = mvc.perform(get(Routes.USER_ROUTE, newUserCip)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Then previously new user information sent and information received are equal
        returnedJson = result.getResponse().getContentAsString();
        assertThat(returnedJson).isEqualTo(newUserJson);

        // When getting new user configuration
        result = mvc.perform(get(Routes.USER_CONFIGURATIONS_ROUTE, newUserCip)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Then auto generated information inside database are equal to expected information
        returnedJson = result.getResponse().getContentAsString();
        assertThat(returnedJson).isEqualTo(newUserConfigurationJson);

        // Given updating configuration information
        ConfigurationContract newConfiguration = ConfigurationContract.builder()
                .withEmailEnabled(false)
                .withEmails(Arrays.asList(newUserCip + "@usherbrooke.ca", newUserCip + "@gmail.ca"))
                .build();
        String newConfigurationJson = objectMapper.writeValueAsString(newConfiguration);

        // When updating configuration
        result = mvc.perform(put(Routes.USER_CONFIGURATIONS_ROUTE, newUserCip)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newConfigurationJson))
                .andExpect(status().isOk())
                .andReturn();

        // Then updated configuration is not equal to original configuration information
        returnedJson = result.getResponse().getContentAsString();
        assertThat(returnedJson).isNotEqualTo(newUserConfigurationJson);

        // When deleting user
        mvc.perform(delete(Routes.USER_CONFIGURATIONS_ROUTE, newUserCip)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Then when getting deleted user, user is not found (Error 404)
        mvc.perform(get(Routes.USER_ROUTE, newUserCip)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

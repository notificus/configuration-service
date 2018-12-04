package configuration.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.api.configuration.ConfigurationController;
import configuration.service.configuration.Configuration;
import configuration.service.configuration.ConfigurationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class TestConfigurationController {
    @MockBean
    private ConfigurationService configurationService;

    private MockMvc mvc;
    private String cip;
    private Configuration configuration;
    private String configurationJson;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders
                .standaloneSetup(new ConfigurationController(configurationService))
                .build();
        cip = "test1111";
        configuration = Configuration
                .builder()
                .withEmailEnabled(false)
                .withEmails(Arrays.asList(cip + "@usherbrooke.ca"))
                .build();
        configurationJson = new ObjectMapper().writeValueAsString(configuration);
    }

    @Test
    public void getConfiguration_whenSuccessfulRequest_ReturnsOK_And_Configuration() throws Exception {
        // Given
        given(configurationService.getConfiguration(anyString())).willReturn(configuration);

        // When
        MvcResult result = mvc.perform(get(Routes.USER_CONFIGURATIONS_ROUTE, cip)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        assertThat(json).isEqualTo(configurationJson);
    }

    @Test
    public void updateUser_whenSuccessfulRequest_ReturnsOK_and_UpdatedConfigurations() throws Exception {
        // Given
        given(configurationService.updateConfiguration(anyString(), any(Configuration.class))).willReturn(configuration);

        // When
        MvcResult result = mvc.perform(put(Routes.USER_CONFIGURATIONS_ROUTE, cip)
                .content(configurationJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        assertThat(json).isEqualTo(configurationJson);
    }

    @Test
    public void deleteConfigurations_whenSuccessfulRequest_ReturnsOK_And_User() throws Exception {
        // Given
        doNothing().when(configurationService).deleteConfiguration(anyString());

        // When
        mvc.perform(delete(Routes.USER_CONFIGURATIONS_ROUTE, cip)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
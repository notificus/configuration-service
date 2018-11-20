package service;

import configuration.service.configuration.Configuration;
import configuration.service.configuration.persistent.ConfigurationEntity;
import configuration.service.configuration.persistent.ConfigurationRepository;
import configuration.service.configuration.persistent.PersistentConfigurationService;
import configuration.service.configuration.persistent.exception.UserConfigurationNotFoundException;
import configuration.service.configuration.persistent.postgresql.EmailConfigurationEntity;
import configuration.service.user.persistent.PersistentUserService;
import configuration.service.user.persistent.UserEntity;
import configuration.service.user.persistent.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class TestConfigurationService {
    @TestConfiguration
    static class PersistentConfigurationServiceContextConfiguration {
        @Bean
        public PersistentConfigurationService configurationService() {
            return new PersistentConfigurationService();
        }
    }

    @TestConfiguration
    static class PersistentUserServiceContextConfiguration {
        @Bean
        public PersistentUserService userService() {
            return new PersistentUserService();
        }
    }

    @Autowired
    private PersistentConfigurationService configurationService;

    @Autowired
    private PersistentUserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ConfigurationRepository configurationRepository;

    @Test
    public void Given_cip_and_configuration_exists_when_getConfiguration_return_configuration() {
        String cip = "test1111";

        UserEntity userEntity = new UserEntity();
        userEntity.setCip(cip);
        userEntity.setFirstName("Test");
        userEntity.setLastName("Testing");

        EmailConfigurationEntity emailConfigurationEntity = new EmailConfigurationEntity();
        emailConfigurationEntity.setId(1);
        emailConfigurationEntity.setEnabled(true);

        List<String> emails = new ArrayList<>();
        emails.add("test1111@usherbrooke.ca");

        emailConfigurationEntity.setEmails(emails);

        ConfigurationEntity configurationEntity = new ConfigurationEntity();
        configurationEntity.setUserEntity(userEntity);
        configurationEntity.setEmailConfigurationEntity(emailConfigurationEntity);

        Mockito.when(configurationRepository.findById(cip)).thenReturn(Optional.of((configurationEntity)));

        Configuration expectedConfiguration = Configuration.builder().withEmailEnabled(true).withEmails(emails).build();

        Configuration configuration = configurationService.getConfiguration(cip);

        assertThat(configuration.getEmailEnabled()).isEqualTo(expectedConfiguration.getEmailEnabled());
        assertThat(configuration.getEmails()).isEqualTo(expectedConfiguration.getEmails());
    }

    @Test(expected = UserConfigurationNotFoundException.class)
    public void Given_cip_and_configuration_does_not_exists_when_getConfiguration_then_throw_exception() {
        String cip = "test1111";
        Mockito.when(configurationRepository.findById(cip)).thenReturn(Optional.empty());

        Configuration configuration = configurationService.getConfiguration(cip);
    }

    @Test
    public void Given_cip_and_configuration_when_updateConfiguration_return_updated_configuration() {
        String cip = "test1111";

        UserEntity userEntity = new UserEntity();
        userEntity.setCip(cip);
        userEntity.setFirstName("Test");
        userEntity.setLastName("Testing");

        List<String> emails = new ArrayList<>();
        emails.add(cip + "@usherbrooke.ca");

        EmailConfigurationEntity emailConfigurationEntity = new EmailConfigurationEntity();
        emailConfigurationEntity.setId(0);
        emailConfigurationEntity.setEnabled(true);
        emailConfigurationEntity.setEmails(emails);

        ConfigurationEntity configurationEntity = new ConfigurationEntity();
        configurationEntity.setUserEntity(userEntity);
        configurationEntity.setEmailConfigurationEntity(emailConfigurationEntity);

        Mockito.when(configurationRepository.findById(cip)).thenReturn(Optional.of(configurationEntity));
        Mockito.when(configurationRepository.save(configurationEntity)).thenReturn(configurationEntity);

        Configuration expectedConfiguration = Configuration.builder().withEmailEnabled(true).withEmails(emails).build();

        Configuration configuration = configurationService.updateConfiguration(cip,
                Configuration.builder()
                        .withEmailEnabled(true)
                        .withEmails(emails)
                        .build());

        assertThat(configuration.getEmailEnabled()).isEqualTo(expectedConfiguration.getEmailEnabled());
        assertThat(configuration.getEmails()).isEqualTo(expectedConfiguration.getEmails());
    }

    @Test(expected = UserConfigurationNotFoundException.class)
    public void Given_cip_and_configuration_but_configurationEntity_does_not_exist_when_updateConfiguration_then_throw_exception() {
        String cip = "test1111";

        Mockito.when(configurationRepository.findById(cip)).thenReturn(Optional.empty());

        Configuration configuration = configurationService.updateConfiguration(cip, null);
    }
}

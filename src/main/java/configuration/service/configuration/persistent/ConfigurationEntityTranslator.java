package configuration.service.configuration.persistent;

import configuration.service.configuration.Configuration;
import configuration.service.configuration.persistent.postgresql.EmailConfigurationEntity;
import configuration.service.user.User;
import configuration.service.user.persistent.PersistentUserService;
import configuration.service.user.persistent.UserEntityTranslator;
import org.springframework.beans.factory.annotation.Autowired;

public class ConfigurationEntityTranslator {
    @Autowired
    PersistentUserService persistentUserService;

    public static ConfigurationEntity translateTo(User user, Configuration configuration) {
        ConfigurationEntity configurationEntity = new ConfigurationEntity();
        EmailConfigurationEntity emailConfigurationEntity = new EmailConfigurationEntity();

        emailConfigurationEntity.setEnabled(configuration.getEmailEnabled());
        emailConfigurationEntity.setEmails(configuration.getEmails());

        configurationEntity.setUserEntity(UserEntityTranslator.translateTo(user));
        configurationEntity.setEmailConfigurationEntity(emailConfigurationEntity);

        return configurationEntity;
    }

    public static Configuration translateFrom(ConfigurationEntity configurationEntity) {
        return Configuration
                .builder()
                .withEmailEnabled(configurationEntity.getEmailConfigurationEntity().getEnabled())
                .withEmails(configurationEntity.getEmailConfigurationEntity().getEmails())
                .build();
    }
}

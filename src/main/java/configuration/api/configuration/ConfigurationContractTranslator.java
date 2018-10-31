package configuration.api.configuration;

import configuration.service.configuration.Configuration;

public class ConfigurationContractTranslator {
    public static ConfigurationContract translateTo(Configuration configuration){
        return ConfigurationContract.builder()
                .withEmailEnabled(configuration.getEmailEnabled())
                .withEmails(configuration.getEmails())
                .build();
    }

    public static Configuration translateFrom(ConfigurationContract configurationContract){
        return Configuration.builder()
                .withEmailEnabled(configurationContract.getEmailEnabled())
                .withEmails(configurationContract.getEmails())
                .build();
    }
}

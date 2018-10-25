package configuration.api.configuration;

import configuration.service.configuration.Configuration;

public class ConfigurationContractTranslator {
    public static ConfigurationContract translateTo(Configuration configuration){
        return ConfigurationContract.builder()
                .withWantsEmail(configuration.getWantsEmail())
                .withEmails(configuration.getEmails())
                .build();
    }

    public static Configuration translateFrom(ConfigurationContract configurationContract){
        return Configuration.builder()
                .withWantsEmail(configurationContract.getWantsEmail())
                .withEmails(configurationContract.getEmails())
                .build();
    }
}

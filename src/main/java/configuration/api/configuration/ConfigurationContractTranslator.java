package configuration.api.configuration;

import configuration.service.configuration.Configuration;

public class ConfigurationContractTranslator {
    public static ConfigurationContract translateTo(Configuration configuration){
        return ConfigurationContract.builder()
                .withCip(configuration.getCip())
                .withWantsEmail(configuration.getWantsEmail())
                .withEmails(configuration.getEmails())
                .build();
    }

    public static Configuration translateFrom(ConfigurationContract configurationContract){
        return Configuration.builder()
                .withCip(configurationContract.getCip())
                .withWantsEmail(configurationContract.getWantsEmail())
                .withEmails(configurationContract.getEmails())
                .build();
    }
}

package configuration.service.configuration.persistent;

import configuration.service.configuration.Configuration;

public class ConfigurationEntityTranslator {
    public static ConfigurationEntity translateTo(Configuration configuration){
        return ConfigurationEntity.builder()
                .withCip(configuration.getCip())
                .withWantsEmail(configuration.getWantsEmail())
                .withEmails(configuration.getEmails())
                .build();
    }

    public static Configuration translateFrom(ConfigurationEntity configurationEntity){
        return Configuration.builder()
                .withCip(configurationEntity.getCip())
                .withWantsEmail(configurationEntity.getWantsEmail())
                .withEmails(configurationEntity.getEmails())
                .build();
    }
}

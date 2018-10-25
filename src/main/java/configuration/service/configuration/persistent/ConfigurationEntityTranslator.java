package configuration.service.configuration.persistent;

import configuration.service.configuration.Configuration;

public class ConfigurationEntityTranslator {
    public static ConfigurationEntity translateTo(Configuration configuration){
        return ConfigurationEntity.builder()
                .build();
    }

    public static Configuration translateFrom(ConfigurationEntity configurationEntity){
        return Configuration.builder()
                .build();
    }
}

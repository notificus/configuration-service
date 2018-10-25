package configuration.service.configuration.persistent;

import configuration.service.configuration.Configuration;

public interface ConfigurationRepository {
    ConfigurationEntity getConfiguration(String cip);
    ConfigurationEntity createConfiguration(ConfigurationEntity configuration);
    ConfigurationEntity updateConfiguration(ConfigurationEntity configuration);
}

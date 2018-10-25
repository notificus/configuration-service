package configuration.service.configuration.persistent.postgresql;

import configuration.service.configuration.Configuration;
import configuration.service.configuration.persistent.ConfigurationEntity;
import configuration.service.configuration.persistent.ConfigurationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PostgresqlConfigurationRepository implements ConfigurationRepository {
    @Override
    public ConfigurationEntity getConfiguration(String cip) {
        return null;
    }

    @Override
    public ConfigurationEntity createConfiguration(ConfigurationEntity configuration) {
        return null;
    }

    @Override
    public ConfigurationEntity updateConfiguration(ConfigurationEntity configuration) {
        return null;
    }
}

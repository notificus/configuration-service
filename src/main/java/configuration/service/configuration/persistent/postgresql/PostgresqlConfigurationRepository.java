package configuration.service.configuration.persistent.postgresql;

import configuration.service.configuration.Configuration;
import configuration.service.configuration.persistent.ConfigurationEntity;
import configuration.service.configuration.persistent.ConfigurationRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostgresqlConfigurationRepository implements ConfigurationRepository {
    @Override
    public ConfigurationEntity getConfiguration(String cip) {
        List<String> emails = new ArrayList<>();
        emails.add(cip + "@usherbrooke.ca");

        return ConfigurationEntity.builder()
                .withCip(cip)
                .withWantsEmail(true)
                .withEmails(emails)
                .build();
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

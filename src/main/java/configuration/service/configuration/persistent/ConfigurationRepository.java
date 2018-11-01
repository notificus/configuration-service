package configuration.service.configuration.persistent;

import configuration.service.configuration.persistent.postgresql.ConfigurationEntityId;
import org.springframework.data.repository.CrudRepository;

public interface ConfigurationRepository extends CrudRepository<ConfigurationEntity, String> {
}

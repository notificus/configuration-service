package configuration.service.configuration.persistent;

import org.springframework.data.repository.CrudRepository;

public interface ConfigurationRepository extends CrudRepository<ConfigurationEntity, String> {
}

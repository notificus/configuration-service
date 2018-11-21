package configuration.service.configuration.persistent;

import configuration.service.configuration.Configuration;
import configuration.service.configuration.ConfigurationService;
import configuration.service.configuration.exception.UserConfigurationNotFoundException;
import configuration.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersistentConfigurationService implements ConfigurationService {
    @Autowired
    ConfigurationRepository configurationRepository;

    @Autowired
    UserService userService;

    @Override
    public Configuration getConfiguration(String cip) {
        Optional<ConfigurationEntity> configurationEntity = configurationRepository.findById(cip);

        if (configurationEntity.isPresent()) {
            return ConfigurationEntityTranslator.translateFrom(configurationEntity.get());
        } else {
            throw new UserConfigurationNotFoundException(cip);
        }
    }

    @Override
    public Configuration updateConfiguration(String cip, Configuration configuration) {
        Optional<ConfigurationEntity> configurationEntity = configurationRepository.findById(cip);

        if (configurationEntity.isPresent()) {
            return ConfigurationEntityTranslator.translateFrom(
                    configurationRepository.save(ConfigurationEntityTranslator.translateTo(
                            configuration, configurationEntity.get())));
        } else {
            throw new UserConfigurationNotFoundException(cip);
        }
    }

    @Override
    public void deleteConfiguration(String cip) {
        Optional<ConfigurationEntity> configurationEntity = configurationRepository.findById(cip);

        if (configurationEntity.isPresent()) {
            configurationRepository.delete(configurationEntity.get());
        } else {
            throw new UserConfigurationNotFoundException(cip);
        }
    }
}

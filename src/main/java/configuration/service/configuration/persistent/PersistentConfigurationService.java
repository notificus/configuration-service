package configuration.service.configuration.persistent;

import configuration.service.configuration.Configuration;
import configuration.service.configuration.ConfigurationService;
import configuration.service.configuration.persistent.postgresql.ConfigurationEntityId;
import configuration.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistentConfigurationService implements ConfigurationService {
    @Autowired
    ConfigurationRepository configurationRepository;

    @Autowired
    UserService userService;

    @Override
    public Configuration getConfiguration(String cip) {
        return ConfigurationEntityTranslator.translateFrom(
                configurationRepository.findById(cip).get());
    }

    @Override
    public Configuration updateConfiguration(String cip, Configuration configuration) {
        return ConfigurationEntityTranslator.translateFrom(
                configurationRepository.save(
                        ConfigurationEntityTranslator.translateTo(userService.getUser(cip), configuration)));
    }
}

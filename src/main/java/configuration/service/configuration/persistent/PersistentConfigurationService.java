package configuration.service.configuration.persistent;

import configuration.service.configuration.Configuration;
import configuration.service.configuration.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistentConfigurationService implements ConfigurationService {
    @Autowired
    ConfigurationRepository configurationRepository;

    @Override
    public Configuration getConfiguration(String cip) {
        try {
            return ConfigurationEntityTranslator.translateFrom(configurationRepository.getConfiguration(cip));
        } catch(Exception e) {
            return createConfiguration(Configuration.builder().withCip(cip).withWantsEmail(false).build());
        }
    }

    @Override
    public Configuration createConfiguration(Configuration configuration){
        return ConfigurationEntityTranslator.translateFrom(configurationRepository.createConfiguration(ConfigurationEntityTranslator.translateTo(configuration)));
    }

    @Override
    public Configuration updateConfiguration(Configuration configuration) {
        return ConfigurationEntityTranslator.translateFrom(configurationRepository.updateConfiguration(ConfigurationEntityTranslator.translateTo(configuration)));
    }
}

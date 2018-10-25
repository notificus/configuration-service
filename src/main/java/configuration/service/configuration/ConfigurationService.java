package configuration.service.configuration;

public interface ConfigurationService {
    Configuration getConfiguration(String cip);
    Configuration createConfiguration(Configuration configuration);
    Configuration updateConfiguration(Configuration configuration);
}

package configuration.service.configuration;

public interface ConfigurationService {
    Configuration getConfiguration(String cip);
    Configuration updateConfiguration(String cip, Configuration configuration);
}

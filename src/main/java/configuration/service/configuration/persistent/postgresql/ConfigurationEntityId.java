package configuration.service.configuration.persistent.postgresql;

import configuration.service.user.persistent.UserEntity;

import java.io.Serializable;

public class ConfigurationEntityId implements Serializable {
    public UserEntity userEntity;
    public EmailConfigurationEntity emailConfigurationEntity;
}

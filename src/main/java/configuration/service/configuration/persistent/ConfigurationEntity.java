package configuration.service.configuration.persistent;

import configuration.service.configuration.persistent.postgresql.EmailConfigurationEntity;
import configuration.service.configuration.persistent.postgresql.ConfigurationEntityId;
import configuration.service.user.persistent.UserEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(ConfigurationEntityId.class)
public class ConfigurationEntity implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "user_entity_cip")
    public UserEntity userEntity;

    @Id
    @OneToOne
    @JoinColumn(name = "email_configuration_entity_id")
    public EmailConfigurationEntity emailConfigurationEntity;

    public ConfigurationEntity() {
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public EmailConfigurationEntity getEmailConfigurationEntity() {
        return emailConfigurationEntity;
    }

    public void setEmailConfigurationEntity(EmailConfigurationEntity emailConfigurationEntity) {
        this.emailConfigurationEntity = emailConfigurationEntity;
    }
}

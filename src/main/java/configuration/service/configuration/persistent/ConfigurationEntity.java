package configuration.service.configuration.persistent;

import configuration.service.configuration.persistent.postgresql.ConfigurationEntityId;
import configuration.service.configuration.persistent.postgresql.EmailConfigurationEntity;
import configuration.service.user.persistent.UserEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ConfigurationEntity {
    @Id
    @Column(name = "user_entity_cip")
    private String cip;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_entity_cip")
    private UserEntity userEntity;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "email_configuration_entity_id")
    private EmailConfigurationEntity emailConfigurationEntity;

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

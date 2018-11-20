package configuration.service.configuration.persistent.postgresql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EmailConfigurationEntity {
    @Id
    @Column(name = "email_configuration_entity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email_configuration_entity_enabled")
    private Boolean enabled;

    @ElementCollection
    @Column(name = "email_configuration_entity_emails")
    private List<String> emails = new ArrayList<String>();

    public EmailConfigurationEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}

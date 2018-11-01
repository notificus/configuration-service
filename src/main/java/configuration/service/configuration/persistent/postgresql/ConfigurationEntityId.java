package configuration.service.configuration.persistent.postgresql;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ConfigurationEntityId implements Serializable {
    String cip;

    public ConfigurationEntityId(String cip) {
        this.cip = cip;
    }
}

package configuration.api.configuration;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import configuration.service.configuration.Configuration;

import java.util.List;

@JsonDeserialize(builder = ConfigurationContract.Builder.class)
public class ConfigurationContract {
    private Boolean emailEnabled;
    private List<String> emails;

    private ConfigurationContract(Builder builder) {
        this.emailEnabled = builder.emailEnabled;
        this.emails = builder.emails;
    }

    public Boolean getEmailEnabled() {
        return emailEnabled;
    }

    public List<String> getEmails() {
        return emails;
    }

    public static Builder builder() {
        return new ConfigurationContract.Builder();
    }

    public static class Builder {
        private Boolean emailEnabled;
        private List<String> emails;

        private Builder() {
        }

        public Builder withEmailEnabled(Boolean enabled) {
            this.emailEnabled = enabled;
            return this;
        }

        public Builder withEmails(List<String> emails) {
            this.emails = emails;
            return this;
        }

        public ConfigurationContract build() {
            return new ConfigurationContract(this);
        }
    }
}

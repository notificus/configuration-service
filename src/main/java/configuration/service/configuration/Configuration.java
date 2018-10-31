package configuration.service.configuration;

import java.util.List;

public class Configuration {
    private Boolean emailEnabled;
    private List<String> emails;

    private Configuration(Builder builder) {
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
        return new Configuration.Builder();
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

        public Configuration build() {
            return new Configuration(this);
        }
    }
}

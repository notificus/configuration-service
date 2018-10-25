package configuration.api.configuration;

import java.util.List;

public class ConfigurationContract {
    private String cip;
    private boolean wantsEmail;
    private List<String> emails;

    private ConfigurationContract(Builder builder) {
        this.cip = builder.cip;
        this.wantsEmail = builder.wantsEmail;
        this.emails = builder.emails;
    }

    public String getCip() { return cip; }

    public boolean getWantsEmail() { return wantsEmail; }

    public List<String> getEmails() { return emails; }

    public static Builder builder() { return new ConfigurationContract.Builder(); }

    public static class Builder {
        private String cip;
        private boolean wantsEmail;
        private List<String> emails;

        private Builder() { }

        public Builder withCip(String cip){
            this.cip = cip;
            return this;
        }

        public Builder withWantsEmail(boolean wantsEmail) {
            this.wantsEmail = wantsEmail;
            return this;
        }

        public Builder withEmails(List<String> emails){
            this.emails = emails;
            return this;
        }

        public ConfigurationContract build() { return new ConfigurationContract(this); }
    }
}

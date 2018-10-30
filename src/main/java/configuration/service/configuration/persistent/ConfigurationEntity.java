package configuration.service.configuration.persistent;

import java.util.List;

public class ConfigurationEntity {
    private String cip;
    private boolean wantsEmail;
    private List<String> emails;

    private ConfigurationEntity(Builder builder){
        this.cip = builder.cip;
        this.wantsEmail = builder.wantsEmails;
        this.emails = builder.emails;
    }

    public String getCip() { return cip; }

    public boolean getWantsEmail(){ return wantsEmail; }

    public List<String> getEmails() { return emails; }

    public static Builder builder(){ return new ConfigurationEntity.Builder(); }

    public static class Builder {
        private String cip;
        private boolean wantsEmails;
        private List<String> emails;

        private Builder(){}

        public Builder withCip(String cip){
            this.cip = cip;
            return this;
        }

        public Builder withWantsEmail(boolean wantsEmail){
            this.wantsEmails = wantsEmail;
            return this;
        }

        public Builder withEmails(List<String> emails){
            this.emails = emails;
            return this;
        }

        public ConfigurationEntity build() { return new ConfigurationEntity(this); }
    }
}

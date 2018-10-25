package configuration.service.configuration;

import java.util.List;

public class Configuration {
    private String cip;
    private boolean wantsEmail;
    private List<String> emails;

    private Configuration(Builder builder) {
        this.cip = builder.cip;
        this.wantsEmail = builder.wantsEmail;
        this.emails = builder.emails;
    }

    public String getCip(){ return cip; }

    public boolean getWantsEmail(){ return wantsEmail; }

    public List<String> getEmails(){ return emails; }

    public static Builder builder() { return new Configuration.Builder(); }

    public static class Builder {
        private String cip;
        private boolean wantsEmail;
        private List<String> emails;

        private Builder(){}

        public Builder withCip(String cip){
            this.cip = cip;
            return this;
        }

        public Builder withWantsEmail(boolean wantsEmail){
            this.wantsEmail = wantsEmail;
            return this;
        }

        public Builder withEmails(List<String> emails){
            this.emails = emails;
            return this;
        }

        public Configuration build(){ return new Configuration(this); }
    }
}

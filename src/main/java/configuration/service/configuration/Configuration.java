package configuration.service.configuration;

import java.util.List;

public class Configuration {
    private boolean wantsEmail;
    private List<String> emails;

    private Configuration(Builder builder) {
        this.wantsEmail = builder.wantsEmail;
        this.emails = builder.emails;
    }

    public boolean getWantsEmail(){ return wantsEmail; }

    public List<String> getEmails(){ return emails; }

    public static Builder builder() { return new Configuration.Builder(); }

    public static class Builder {
        private boolean wantsEmail;
        private List<String> emails;

        private Builder(){}

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

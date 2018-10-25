package configuration.service.configuration;

public class Configuration {
    private boolean wantsEmail;

    private Configuration(Builder builder) {
        this.wantsEmail = builder.wantsEmail;
    }

    public boolean getWantsEmail(){
        return wantsEmail;
    }

    public static Builder builder() { return new Configuration.Builder(); }

    public static class Builder {
        private boolean wantsEmail;

        private Builder(){}

        public Builder withWantsEmail(boolean wantsEmail){
            this.wantsEmail = wantsEmail;
            return this;
        }

        public Configuration build(){ return new Configuration(this); }
    }
}

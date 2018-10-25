package configuration.api.configuration;

public class ConfigurationContract {
    private boolean wantsEmail;

    private ConfigurationContract(Builder builder) {
        this.wantsEmail = builder.wantsEmail;
    }

    public boolean getWantsEmail() {
        return wantsEmail;
    }

    public static Builder builder() {
        return new ConfigurationContract.Builder();
    }

    public static class Builder {
        private boolean wantsEmail;

        private Builder() {
        }

        public Builder withWantsEmail(boolean wantsEmail) {
            this.wantsEmail = wantsEmail;
            return this;
        }

        public ConfigurationContract build() {
            return new ConfigurationContract(this);
        }
    }
}

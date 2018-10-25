package configuration.service.configuration.persistent;

public class ConfigurationEntity {
    private ConfigurationEntity(Builder builder){

    }

    public static Builder builder(){ return new ConfigurationEntity.Builder(); }

    public static class Builder {
        private Builder(){}

        public ConfigurationEntity build() { return new ConfigurationEntity(this); }
    }
}

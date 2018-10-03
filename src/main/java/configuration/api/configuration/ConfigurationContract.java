package configuration.api.configuration;

public class ConfigurationContract {

}
public class Placeholder1 {
    private String value;

    private Placeholder1(Builder builder) {
        this.value = builder.value;
    }

    public String getValue() {
        return value;
    }

    private Builder builder() {
        return new Builder();
    }

    private class Builder {
        private String value;

        public void withValue(String value) {
            this.value = value;
        }

        public Placeholder1 build() {
            return new Placeholder1(this);
        }
    }
}

package configuration.api.user;

public class UserContract {
    private String cip;
    private String firstName;
    private String lastName;

    private UserContract(Builder builder) {
        this.cip = builder.cip;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public String getCip() {
        return cip;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static Builder builder() {
        return new UserContract.Builder();
    }

    public static class Builder {
        private String cip;
        private String firstName;
        private String lastName;

        private Builder() {}

        public Builder withCip(String cip) {
            this.cip = cip;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserContract build() {
            return new UserContract(this);
        }
    }
}

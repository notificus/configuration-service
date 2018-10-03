package configuration.api.user;

public class UserContract {
    private String cip;
    private String firstName;
    private String lastName;
    private String department;
    private String faculty;

    private UserContract(Builder builder) {
        this.cip = builder.cip;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.department = builder.department;
        this.faculty = builder.faculty;
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

    public String getDepartment() {
        return department;
    }

    public String getFaculty() {
        return faculty;
    }

    private Builder builder() {
        return new Builder();
    }

    private class Builder {
        private String cip;
        private String firstName;
        private String lastName;
        private String department;
        private String faculty;

        public void withCip(String cip) {
            this.cip = cip;
        }

        public void withFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void withLastName(String lastName) {
            this.lastName = lastName;
        }

        public void withDepartment(String department) {
            this.department = department;
        }

        public void withFaculty(String faculty) {
            this.faculty = faculty;
        }

        public UserContract build() {
            return new UserContract(this);
        }
    }
}

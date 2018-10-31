package configuration.service.user.persistent;

import javax.persistence.*;

@Entity
public class UserEntity {
    @Id
    @Column(name = "user_entity_cip")
    private String cip;

    @Column(name = "user_entity_first_name")
    private String firstName;

    @Column(name = "user_entity_last_name")
    private String lastName;

    public UserEntity() {
    }

    public String getCip() {
        return cip;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }
}

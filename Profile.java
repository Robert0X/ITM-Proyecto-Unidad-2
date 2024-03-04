import java.util.Date;
import java.util.UUID;

public class Profile {

    private UUID id;
    private String name;
    private String lastName;
    private Date birthdate;

    public Profile(String name, String lastName, Date birthdate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
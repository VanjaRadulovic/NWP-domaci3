package rs.raf.demo.requests;

import rs.raf.demo.model.Permission;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class UpdateUserRequest {

    private String email;
    private String firstName;
    private String lastName;

    private Set<Permission> permissions;

    public UpdateUserRequest(String email, String firstName, String lastName, Set<Permission> permissions) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.permissions = permissions;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}


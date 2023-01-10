package rs.raf.demo.responses;

import lombok.Data;
import rs.raf.demo.model.Permission;

import java.util.List;

@Data
public class LoginResponse {
    private String jwt;

    private List<Permission> permissionm;

    public LoginResponse(String jwt, List<Permission> permission) {
        this.jwt = jwt;
        this.permissionm = permission;
    }
}

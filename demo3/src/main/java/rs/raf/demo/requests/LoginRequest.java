package rs.raf.demo.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data

public class LoginRequest {
    @NotBlank(message = "Email is mandatory.")
    private String email;
    @NotBlank(message = "Password is mandatory.")
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

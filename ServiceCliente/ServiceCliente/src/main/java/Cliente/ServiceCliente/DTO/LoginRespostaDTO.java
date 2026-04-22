package Cliente.ServiceCliente.DTO;

import Cliente.ServiceCliente.Model.Role;
import jakarta.validation.constraints.Email;


public class LoginRespostaDTO {
   
    @Email(message = "the email must be valid")
    private String email;
    private String token;
    private Role role;
    public LoginRespostaDTO() {
    }
    public LoginRespostaDTO(String token, String email, String role) {
        this.token = token;
        this.email = email;
        this.role = Role.valueOf(role);
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

}

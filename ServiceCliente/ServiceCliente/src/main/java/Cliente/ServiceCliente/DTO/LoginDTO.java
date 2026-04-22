package Cliente.ServiceCliente.DTO;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
     
    @NotBlank(message = "the email is required")
    private String email;

    @NotBlank(message = "the password is required")
    private String password;
    
    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public LoginDTO() {
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

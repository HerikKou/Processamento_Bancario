package Cliente.ServiceCliente.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteRespostaDTO {
    private Long id;
    @NotBlank(message = "the name is required")
    private String name;
    @Email(message = "the email must be valid")
    private String email;
    @NotBlank(message = "the phone is required")
    private String phone;
    

    public ClienteRespostaDTO() {
    }

    public ClienteRespostaDTO(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}


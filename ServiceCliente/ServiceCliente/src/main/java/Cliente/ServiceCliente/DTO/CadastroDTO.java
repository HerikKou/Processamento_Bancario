package Cliente.ServiceCliente.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CadastroDTO {
    @NotBlank(message = "the name is required")
    private String name;
    @Email(message = "the email must be valid")
    private String email;
    @NotBlank(message = "the phone is required")
    private String phone;
    @NotBlank(message = "the CPF is required")
    private String cpf;
    @NotBlank(message = "the password is required")
    private String password;
    public CadastroDTO() {
    }
    public CadastroDTO( String name,String email,String phone,String cpf,
String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.password = password;
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
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}

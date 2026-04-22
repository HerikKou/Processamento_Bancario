package Conta.ServiceConta.DTO;

import Conta.ServiceConta.Enum.Roles;



public class ContaRespostaDTO{
    
    private String agency;
   
    private String accountNumber;
   
    private Long clientId;
    
    private String accountType;
    private Roles role;
    public ContaRespostaDTO(){}

    public ContaRespostaDTO(String agency, String accountNumber, Long clientId, String accountType, Roles role) {
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.clientId = clientId;
        this.accountType = accountType;
        this.role = role;
    }
    public String getAgency() {
        return agency;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAgency(String agency) {
        this.agency = agency;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Roles getRole() {
        return role;
    }
    public void setRole(Roles role) {
        this.role = role;
    }


}

package Conta.ServiceConta.DTO;

import Conta.ServiceConta.Enum.TypeAccount;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;


public class ContaDTO{
    
    @NotNull(message = "the agency is required")
    private String agency;
    @NotNull(message = "the clientID is required")
    private Long clientId;
    @NotNull(message = "the balance is required")
    private Double balance;
	@NotNull(message = "the value is required")
	private Double value;
    @Enumerated(EnumType.STRING)
    private TypeAccount accountType;
    
    public ContaDTO(){}

    public ContaDTO(String agency, Long clientId, Double balance,Double value, TypeAccount accountType){

	this.agency = agency;
	this.clientId = clientId;
	this.balance = balance;
	this.value = value;
	this.accountType = accountType;
}

public String getAgency() {
		return agency;
	}
public Long getClientId(){
	return clientId;
}
public Double getBalance(){
	return balance;
}
public TypeAccount getAccountType(){
	return accountType;
}
public void setAgency(String agency) {
	this.agency = agency;
}
public void setClientId(Long clientId){
	this.clientId = clientId;
}
public void setBalance(Double balance){
	this.balance = balance;
}
public void setAccountType(TypeAccount accountType){
  	this.accountType = accountType;
}

public Double getValue() {
	return value;
}

public void setValue(Double value) {
	this.value = value;
}


}

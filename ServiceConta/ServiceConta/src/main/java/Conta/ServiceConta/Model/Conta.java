package Conta.ServiceConta.Model;

import Conta.ServiceConta.Enum.Roles;
import Conta.ServiceConta.Enum.TypeAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Conta{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotBlank(message = "the agency is required")
	private String agency;
    @NotBlank(message = "the accountNumber is required")
    private String accountNumber;
    @NotNull(message = "the clientID is required")
    private Long clientId;
    @NotNull(message = "the balance is required")
    private Double balance;
	@NotNull(message = "the value is required")
	private Double value;
    @Enumerated(EnumType.STRING)
    private TypeAccount accountType;
    @Enumerated(EnumType.STRING)
	private Roles role;
    public Conta(){}

    public Conta(String agency, String accountNumber, Long clientId, Double balance,Double value, TypeAccount accountType, Roles role){
	this.agency = agency;
	this.accountNumber = accountNumber;
	this.clientId = clientId;
	this.balance = balance;
	this.value = value;
	this.accountType = accountType;
	this.role = role;
}

public Long getId(){
	return id;
}	
public String getAgency() {
	return agency;
}
public String getAccountNumber(){
	return accountNumber;
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

public void setId(Long id){
	this.id = id;
}
public void setAgency(String agency){
	this.agency = agency;
}
public void setAccountNumber(String accountNumber){
  	this.accountNumber = accountNumber;
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

public void setBalance(double balance) {
	this.balance = balance;
}

public Roles getRole() {
	return role;
}

public void setRole(Roles role) {
	this.role = role;
}

public Double getValue() {
	return value;
}

public void setValue(Double value) {
	this.value = value;
}


}

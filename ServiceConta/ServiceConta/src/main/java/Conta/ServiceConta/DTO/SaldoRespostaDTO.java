package Conta.ServiceConta.DTO;

import Conta.ServiceConta.Enum.Roles;
import jakarta.validation.constraints.NotNull;


public class SaldoRespostaDTO{
    

    
    private Long accountId;
   
    private Double balance;
    private Roles role;
    public SaldoRespostaDTO(){}

    public SaldoRespostaDTO(Double balance, Roles role){

	this.balance = balance;
	this.role = role;
}

public Long getAccountId(){
	return accountId;
}
public Double getBalance(){
	return balance;
}
public Roles getRole() {
    return role;
}

public void setAccountId(Long accountId){
	this.accountId = accountId;
}
public void setBalance(Double balance){
  	this.balance = balance;
}

public void setRole(Roles role) {
    this.role = role;
}


}

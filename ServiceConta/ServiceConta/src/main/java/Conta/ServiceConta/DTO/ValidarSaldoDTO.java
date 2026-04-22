package Conta.ServiceConta.DTO;



import Conta.ServiceConta.Enum.Roles;



public class ValidarSaldoDTO{
    
   
    private Double balance;
    private Roles role;
    public ValidarSaldoDTO(){}

    public ValidarSaldoDTO(Double balance, Roles role){
           this.balance = balance;
           this.role = role;
} 

public Double getBalance(){
	return balance;
}

public void setBalance(Double balance){
  	this.balance = balance;
}

public Roles getRole() {
    return role;
}

public void setRole(Roles role) {
    this.role = role;
}


}

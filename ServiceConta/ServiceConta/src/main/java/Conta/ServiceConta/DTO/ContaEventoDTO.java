package Conta.ServiceConta.DTO;

import Conta.ServiceConta.Enum.TypeAccount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class ContaEventoDTO{
    

    @NotNull(message = "the accountID is required")
    private Long accountId;
    @NotNull(message = "the accountID is required")
    private Long clientId;
    @NotBlank(message = "the accontType is required")
    private TypeAccount accountType;
    
    public ContaEventoDTO(){}

    public ContaEventoDTO(Long accountId,Long clientId ,TypeAccount accountType){

	this.accountId = accountId;
    this.clientId = clientId;

	this.accountType = accountType;
}

public Long getaccountId(){
	return accountId;
}
public Long getclientId(){
    return clientId;
}

public TypeAccount getAccountType(){
	return accountType;
}

public void setaccountId(Long accountId){
	this.accountId = accountId;
}
public void setClientId(Long clientId){
    this.clientId = clientId;
}
public void setAccountType(TypeAccount accountType){
  	this.accountType = accountType;
}


}

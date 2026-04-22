package Transacao.ServiceTransacao.DTO;

import Transacao.ServiceTransacao.Enum.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class ContaCriadaEventoDTO{
    

    @NotNull(message = "the accountID is required")
    private Long accountId;
    @NotNull(message = "the accountID is required")
    private Long clientId;
    @NotBlank(message = "the accontType is required")
    private TypeAccount accountType;
    
    public ContaCriadaEventoDTO(){}

    public ContaCriadaEventoDTO(Long accountId,Long clientId ,TypeAccount accountType){

	this.accountId = accountId;
    this.clientId = clientId;

	this.accountType = accountType;
}

public Long getAccountId(){
	return accountId;
}
public Long getClientId(){
    return clientId;
}

public TypeAccount getAccountType(){
	return accountType;
}

public void setAccountId(Long accountId){
	this.accountId = accountId;
}
public void setClientId(Long clientId){
    this.clientId = clientId;
}
public void setAccountType(TypeAccount accountType){
  	this.accountType = accountType;
}


}

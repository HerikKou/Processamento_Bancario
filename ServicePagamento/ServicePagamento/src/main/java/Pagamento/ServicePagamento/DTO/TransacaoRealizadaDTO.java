package Pagamento.ServicePagamento.DTO;

import java.time.LocalDateTime;

import Pagamento.ServicePagamento.Enum.*;

public class TransacaoRealizadaDTO {
    private Long transactionId;
    private Long accountId;
    private Long accountDestinyId;
    private Double value;
    private Status status;
    private LocalDateTime date;
    private TypeTransactional type;
    public TransacaoRealizadaDTO() {}
    public TransacaoRealizadaDTO(Long transactionId,Long accountId, Long accountDestinyId, Double value,Status status,LocalDateTime date, TypeTransactional type) {
    this.transactionId = transactionId;
        this.accountId = accountId;
        this.accountDestinyId = accountDestinyId;
        
        this.value = value;
    this.status = status;
     this.date = date;
        this.type = type;
    }
    public Long getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public Long getAccountDestinyId() {
        return accountDestinyId;
    }
    public void setAccountDestinyId(Long accountDestinyId) {
        this.accountDestinyId = accountDestinyId;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public TypeTransactional getType() {
        return type;
    }
    public void setType(TypeTransactional type) {
        this.type = type;
    }
    
}
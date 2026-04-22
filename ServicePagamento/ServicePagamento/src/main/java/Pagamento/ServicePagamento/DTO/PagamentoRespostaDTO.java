package Pagamento.ServicePagamento.DTO;

import java.time.LocalDateTime;

import Pagamento.ServicePagamento.Enum.Roles;
import Pagamento.ServicePagamento.Enum.StatusPagamento;

import Pagamento.ServicePagamento.Enum.TypePagamento;

public class PagamentoRespostaDTO {
    private Long id;
    private Long transactionId;
    private StatusPagamento status;
    private TypePagamento type;
    private Roles role;
    private LocalDateTime createdAt;
    public PagamentoRespostaDTO() {}
    public PagamentoRespostaDTO(Long id, Long transactionId, StatusPagamento status, TypePagamento type,Roles role, LocalDateTime createdAt) {
        this.id = id;
        this.transactionId = transactionId;
        this.status = status;
        this.type = type;
        this.role = role;
        this.createdAt = createdAt;

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
    public StatusPagamento getStatus() {
        return status;
    }
    public void setStatus(StatusPagamento status) {
        this.status = status;
    }
    public TypePagamento getType() {
        return type;
    }
    public void setType(TypePagamento type) {
        this.type = type;
    }
    public Roles getRole() {
        return role;
    }
    public void setRole(Roles role) {
        this.role = role;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
}

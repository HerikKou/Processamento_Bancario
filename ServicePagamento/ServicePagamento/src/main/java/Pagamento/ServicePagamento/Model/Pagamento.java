package Pagamento.ServicePagamento.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import Pagamento.ServicePagamento.Enum.*;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "the transactionId is required")
    private Long transactionId;
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPayment;
    @Enumerated(EnumType.STRING)
    private TypePagamento typePayment;
     private Roles role;
    private LocalDateTime createdAt;
    public Pagamento() {}
    public Pagamento(Long id, @NotNull(message = "the transactionId is required") Long transactionId,
            StatusPagamento statusPayment, TypePagamento typePayment, Roles role, LocalDateTime createdAt) {
        this.id = id;
        this.transactionId = transactionId;
        this.statusPayment = statusPayment;
        this.typePayment = typePayment;
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
    public StatusPagamento getStatusPayment() {
        return statusPayment;
    }
    public void setStatusPayment(StatusPagamento statusPayment) {
        this.statusPayment = statusPayment;
    }
    public TypePagamento getTypePayment() {
        return typePayment;
    }
    public void setTypePayment(TypePagamento typePayment) {
        this.typePayment = typePayment;
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
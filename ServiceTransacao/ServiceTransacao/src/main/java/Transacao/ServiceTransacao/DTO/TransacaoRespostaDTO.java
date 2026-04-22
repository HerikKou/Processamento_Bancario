package Transacao.ServiceTransacao.DTO;

import Transacao.ServiceTransacao.Enum.Status;
import Transacao.ServiceTransacao.Enum.TypeTransactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoRespostaDTO {

    private Long id;
    private Long accountId;
    private Long accountDestinyId;
    private Double value;
    private Status status;
    private LocalDateTime date;
    private TypeTransactional type;

    public TransacaoRespostaDTO() {}

    public TransacaoRespostaDTO(Long id, Long accountId, Long accountDestinyId, Double value, Status status, LocalDateTime date, TypeTransactional type) {
        this.id = id;
        this.accountId = accountId;
        this.accountDestinyId = accountDestinyId;
        this.value = value;
        this.status = status;
        this.date = date;
        this.type = type;
    }

    public Long getId() { return id; }
    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }
    public Long getAccountDestinyId() { return accountDestinyId; }
    public void setAccountDestinyId(Long accountDestinyId) { this.accountDestinyId = accountDestinyId; }
    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public TypeTransactional getType() { return type; }
    public void setType(TypeTransactional type) { this.type = type; }
}
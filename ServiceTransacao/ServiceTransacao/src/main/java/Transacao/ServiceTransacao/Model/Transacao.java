package Transacao.ServiceTransacao.Model;
import Transacao.ServiceTransacao.Enum.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import java.time.LocalDateTime;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "the AccountID is required")
   private Long accountId;
    @NotNull(message = "the AccountDestinyID is required")
    private Long accountDestinyId;


    @NotNull(message = "the Value is required")
    private Double value;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private TypeTransactional type;

    public Transacao() {}

    public Transacao(Long accountId, Long accountDestinyId, Double value, Status status, LocalDateTime date, TypeTransactional type) {
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
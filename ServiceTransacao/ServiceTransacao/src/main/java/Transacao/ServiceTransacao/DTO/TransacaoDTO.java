package Transacao.ServiceTransacao.DTO;

import Transacao.ServiceTransacao.Enum.TypeTransactional;
import jakarta.validation.constraints.NotNull;


public class TransacaoDTO {

    @NotNull(message = "the accountId is required")
    private Long accountId;

    @NotNull(message = "the accountDestinyId is required")
    private Long accountDestinyId;

    @NotNull(message = "the value is required")
    private Double value;

    @NotNull(message = "the type is required")
    private TypeTransactional type;

    public TransacaoDTO() {}

    public TransacaoDTO(Long accountId, Long accountDestinyId,  Double value, TypeTransactional type) {
        this.accountId = accountId;
        this.accountDestinyId = accountDestinyId;
        this.value = value;
        this.type = type;
    }

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }
    public Long getAccountDestinyId() { return accountDestinyId; }
    public void setAccountDestinyId(Long accountDestinyId) { this.accountDestinyId = accountDestinyId; }
    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }
    public TypeTransactional getType() { return type; }
    public void setType(TypeTransactional type) { this.type = type; }
}
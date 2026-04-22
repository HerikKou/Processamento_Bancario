package Conta.ServiceConta.DTO;

public class SaldoDTO {
    private Long accountId;
    private Double balance;
    public SaldoDTO(Long accountId, Double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }
    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
}

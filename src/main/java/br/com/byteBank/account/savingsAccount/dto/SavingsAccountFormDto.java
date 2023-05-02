package br.com.byteBank.account.savingsAccount.dto;

import br.com.byteBank.account.savingsAccount.SavingsAccount;
import br.com.byteBank.client.Client;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class SavingsAccountFormDto {

    private Client client;
    private Long clientId;

    @DecimalMin("0.0")
    private BigDecimal balance;

    public SavingsAccountFormDto(Client client, Long clientId, @DecimalMin("0.0") BigDecimal balance) {
        this.client = client;
        this.clientId = clientId;
        this.balance = balance;
    }

    public SavingsAccountFormDto() {
    }

    public SavingsAccount toEntity() {
        return new SavingsAccount(client, balance);
    }

    public Client getClient() {
        return this.client;
    }

    public Long getClientId() {
        return this.clientId;
    }

    public @DecimalMin("0.0") BigDecimal getBalance() {
        return this.balance;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setBalance(@DecimalMin("0.0") BigDecimal balance) {
        this.balance = balance;
    }
}

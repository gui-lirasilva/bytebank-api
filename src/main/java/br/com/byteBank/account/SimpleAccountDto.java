package br.com.byteBank.account;

import br.com.byteBank.account.checkingAccount.CheckingAccount;
import br.com.byteBank.account.savingsAccount.SavingsAccount;

import java.math.BigDecimal;

public class SimpleAccountDto {

    private Long id;
    private Long clientId;
    private BigDecimal balance;

    public SimpleAccountDto(SavingsAccount account) {
        this.id = account.getId();
        this.clientId = account.getClient().getId();
        this.balance = account.getBalance();
    }

    public SimpleAccountDto(CheckingAccount account) {
        this.id = account.getId();
        this.clientId = account.getClient().getId();
        this.balance = account.getBalance();
    }

    public SimpleAccountDto(Long id, Long clientId, BigDecimal balance) {
        this.id = id;
        this.clientId = clientId;
        this.balance = balance;
    }

    public SimpleAccountDto() {
    }

    public Long getId() {
        return this.id;
    }

    public Long getClientId() {
        return this.clientId;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

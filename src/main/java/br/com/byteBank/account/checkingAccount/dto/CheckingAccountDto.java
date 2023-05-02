package br.com.byteBank.account.checkingAccount.dto;

import br.com.byteBank.account.checkingAccount.CheckingAccount;
import br.com.byteBank.client.dto.ClientDto;

import java.math.BigDecimal;

public class CheckingAccountDto {

    private Long id;
    private ClientDto client;
    private BigDecimal balance;

    public CheckingAccountDto(CheckingAccount account) {
        this.id = account.getId();
        this.client = new ClientDto(account.getClient());
        this.balance = account.getBalance();
    }

    public CheckingAccountDto(Long id, ClientDto client, BigDecimal balance) {
        this.id = id;
        this.client = client;
        this.balance = balance;
    }

    public CheckingAccountDto() {
    }

    public Long getId() {
        return this.id;
    }

    public ClientDto getClient() {
        return this.client;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

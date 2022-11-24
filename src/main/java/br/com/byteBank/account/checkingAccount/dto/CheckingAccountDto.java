package br.com.byteBank.account.checkingAccount.dto;

import br.com.byteBank.account.checkingAccount.CheckingAccount;
import br.com.byteBank.client.dto.ClientDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckingAccountDto {

    private Long id;
    private ClientDto client;
    private BigDecimal balance;

    public CheckingAccountDto(CheckingAccount account) {
        this.id = account.getId();
        this.client = new ClientDto(account.getClient());
        this.balance = account.getBalance();
    }
}

package br.com.byteBank.account.savingsAccount.dto;

import br.com.byteBank.account.savingsAccount.SavingsAccount;
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
public class SavingsAccountDto {

    private Long id;
    private ClientDto client;
    private BigDecimal balance;

    public SavingsAccountDto(SavingsAccount account) {
        this.id = account.getId();
        this.client = new ClientDto(account.getClient());
        this.balance = account.getBalance();
    }
}

package br.com.byteBank.account;

import br.com.byteBank.account.checkingAccount.CheckingAccount;
import br.com.byteBank.account.savingsAccount.SavingsAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}

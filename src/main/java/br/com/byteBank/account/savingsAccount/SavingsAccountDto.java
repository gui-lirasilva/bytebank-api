package br.com.byteBank.account.savingsAccount;

import br.com.byteBank.client.Client;
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
    private Client client;
    private BigDecimal balance;

    public SavingsAccountDto(SavingsAccount account) {
        this.client = account.getClient();
        this.balance = account.getBalance();
    }
}

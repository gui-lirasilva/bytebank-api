package br.com.byteBank.account.savingsAccount.dto;

import br.com.byteBank.account.savingsAccount.SavingsAccount;
import br.com.byteBank.client.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavingsAccountFormDto {

    private Client client;
    private Long clientId;

    @DecimalMin("0.0")
    private BigDecimal balance;

    public SavingsAccount toEntity() {
        return new SavingsAccount(client, balance);
    }
}

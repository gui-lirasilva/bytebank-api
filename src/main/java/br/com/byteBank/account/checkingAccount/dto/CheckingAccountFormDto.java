package br.com.byteBank.account.checkingAccount.dto;

import br.com.byteBank.account.checkingAccount.CheckingAccount;
import br.com.byteBank.client.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckingAccountFormDto {
    private Client client;
    private Long clientId;

    @DecimalMin("0.0")
    private BigDecimal balance;

    public CheckingAccount toEntity() {
        return new CheckingAccount(client, balance);
    }
}

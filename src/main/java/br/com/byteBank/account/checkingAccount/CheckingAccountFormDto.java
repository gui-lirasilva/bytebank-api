package br.com.byteBank.account.checkingAccount;

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
public class CheckingAccountFormDto {

    @NotNull
    private Client client;

    @DecimalMin("0.0")
    private BigDecimal balance;
}

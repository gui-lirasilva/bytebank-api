package br.com.byteBank.account.checkingAccount.dto;

import br.com.byteBank.account.checkingAccount.CheckingAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckingAccountSimpleDto {

    private Long id;
    private Long clientId;
    private BigDecimal balance;

    public CheckingAccountSimpleDto(CheckingAccount account) {
        this.id = account.getId();
        this.clientId = account.getClient().getId();
        this.balance = account.getBalance();
    }
}

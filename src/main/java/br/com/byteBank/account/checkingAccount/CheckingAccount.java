package br.com.byteBank.account.checkingAccount;

import br.com.byteBank.account.Account;
import br.com.byteBank.account.checkingAccount.dto.CheckingAccountFormDto;
import br.com.byteBank.client.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CheckingAccount")
public class CheckingAccount extends Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "balance")
    private BigDecimal balance;

    public CheckingAccount(Client client, BigDecimal balance) {
        this.client = client;
        this.balance = balance;
    }

    @Override
    public void transfer(BigDecimal value, Account account) {
        if(balance.doubleValue() < value.doubleValue()) {
            throw new IllegalArgumentException();
        }
        balance = balance.subtract(value.add(BigDecimal.valueOf(0.5)));
        account.recieve(value);
    }

    @Override
    public void recieve(BigDecimal value) {
        balance = balance.add(value);
    }

    public void update(CheckingAccountFormDto formDto) {
        this.balance = formDto.getBalance();
        this.client = formDto.getClient();
    }
}

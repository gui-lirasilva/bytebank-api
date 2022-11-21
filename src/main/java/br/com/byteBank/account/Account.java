package br.com.byteBank.account;

import java.math.BigDecimal;

public abstract class Account {

    public abstract void transfer(BigDecimal value, Account account);
    public abstract void recieve(BigDecimal value);
}

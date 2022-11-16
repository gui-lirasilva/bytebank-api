package br.com.byteBank.account.checkingAccount;

import br.com.byteBank.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {

    public Optional<CheckingAccount> findByClient(Client client);
}

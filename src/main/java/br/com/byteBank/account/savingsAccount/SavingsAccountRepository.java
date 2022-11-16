package br.com.byteBank.account.savingsAccount;

import br.com.byteBank.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

    public Optional<SavingsAccount> findByClient(Client client);
}

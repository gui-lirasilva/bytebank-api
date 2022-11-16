package br.com.byteBank.client;

import br.com.byteBank.account.checkingAccount.CheckingAccount;
import br.com.byteBank.account.savingsAccount.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = """
    SELECT * FROM SavingsAccount sa WHERE sa.client_id = :#{#client.id}
    """, nativeQuery = true)
    public Optional<SavingsAccount> findSavingsAccountByClient(@Param("client") Client client);

    @Query(value = """
    SELECT * FROM CheckingAccount ca WHERE ca.client_id = :#{#client.id}
    """, nativeQuery = true)
    public Optional<CheckingAccount> findCheckingAccountByClient(@Param("client") Client client);
}

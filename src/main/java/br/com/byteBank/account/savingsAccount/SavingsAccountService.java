package br.com.byteBank.account.savingsAccount;

import br.com.byteBank.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Service
public class SavingsAccountService {

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    public List<SavingsAccountDto> listAllSavingsAccounts() {
        return savingsAccountRepository.findAll().stream().map(SavingsAccountDto::new).toList();
    }

    public SavingsAccountDto insertSavingAccount(@Valid SavingsAccountFormDto formDto) {
        SavingsAccount savingsAccount = savingsAccountRepository
                .save(new SavingsAccount(formDto.getClient(), formDto.getBalance()));
        return new SavingsAccountDto(savingsAccount);
    }

    public SavingsAccountDto findSavingsAccountByClient(Client client) {
        SavingsAccount savingsAccount = savingsAccountRepository.findByClient(client)
                .orElseThrow(EntityNotFoundException::new);
        return new SavingsAccountDto(savingsAccount);
    }

    public void deleteCheckingAccount(Long id) {
        savingsAccountRepository.deleteById(id);
    }
}

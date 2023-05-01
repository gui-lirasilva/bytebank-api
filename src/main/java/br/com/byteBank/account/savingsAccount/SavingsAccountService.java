package br.com.byteBank.account.savingsAccount;

import br.com.byteBank.account.AccountType;
import br.com.byteBank.account.TransferInfo;
import br.com.byteBank.account.checkingAccount.CheckingAccount;
import br.com.byteBank.account.checkingAccount.CheckingAccountRepository;
import br.com.byteBank.account.savingsAccount.dto.SavingsAccountFormDto;
import br.com.byteBank.account.AccountSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SavingsAccountService {

    private final SavingsAccountRepository savingsAccountRepository;
    private final CheckingAccountRepository checkingAccountRepository;

    public List<AccountSimpleDto> listAllSavingsAccounts(Pageable pageable) {
        return savingsAccountRepository.findAll(pageable).stream().map(AccountSimpleDto::new).toList();
    }

    @Transactional
    public AccountSimpleDto create(SavingsAccountFormDto formDto) {
        SavingsAccount savingsAccount = savingsAccountRepository.save(formDto.toEntity());
        return new AccountSimpleDto(savingsAccount);
    }

    @Transactional
    public AccountSimpleDto updateAccount(Long id, SavingsAccountFormDto formDto) {
        SavingsAccount savingsAccount = savingsAccountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        savingsAccount.update(formDto);
        savingsAccountRepository.save(savingsAccount);
        return new AccountSimpleDto(savingsAccount);
    }

    @Transactional
    public void deleteSavingsAccount(Long id) {
        savingsAccountRepository.deleteById(id);
    }

    public boolean accountNotExists(Long id) {
        Optional<SavingsAccount> possibleAccount = savingsAccountRepository.findById(id);
        return possibleAccount.isEmpty();
    }

    public Optional<SavingsAccount> findAccountById(Long id) {
        return savingsAccountRepository.findById(id);
    }

    @Transactional
    public void transfer(Long id, TransferInfo transferInfo) {
        SavingsAccount account = savingsAccountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (transferInfo.getDestinationAccountType() == AccountType.SAVINGS) {
            SavingsAccount destinationAccount = savingsAccountRepository.findById(transferInfo.getDestinationId())
                    .orElseThrow(EntityNotFoundException::new);
            account.transfer(transferInfo.getValue(), destinationAccount);
            savingsAccountRepository.save(account);
            savingsAccountRepository.save(destinationAccount);
        } else {
            CheckingAccount destinationAccount = checkingAccountRepository.findById(transferInfo.getDestinationId())
                    .orElseThrow(EntityNotFoundException::new);
            account.transfer(transferInfo.getValue(), destinationAccount);
            savingsAccountRepository.save(account);
            checkingAccountRepository.save(destinationAccount);
        }
    }

    @Transactional
    public void deposit(TransferInfo transferInfo) {
        SavingsAccount account = savingsAccountRepository.findById(transferInfo.getDestinationId())
                .orElseThrow(EntityNotFoundException::new);
        account.recieve(transferInfo.getValue());
        savingsAccountRepository.save(account);
    }
}

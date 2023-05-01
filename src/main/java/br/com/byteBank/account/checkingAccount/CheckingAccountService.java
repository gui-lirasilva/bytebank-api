package br.com.byteBank.account.checkingAccount;

import br.com.byteBank.account.SimpleAccountDto;
import br.com.byteBank.account.AccountType;
import br.com.byteBank.account.TransferInfo;
import br.com.byteBank.account.checkingAccount.dto.CheckingAccountFormDto;
import br.com.byteBank.account.savingsAccount.SavingsAccount;
import br.com.byteBank.account.savingsAccount.SavingsAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckingAccountService {

    private final CheckingAccountRepository checkingAccountRepository;
    private final SavingsAccountRepository savingsAccountRepository;

    public List<SimpleAccountDto> listAllCheckingAccounts(Pageable pageable) {
        return checkingAccountRepository.findAll(pageable).stream().map(SimpleAccountDto::new).toList();
    }

    @Transactional
    public SimpleAccountDto create(CheckingAccountFormDto formDto) {
        CheckingAccount checkingAccount = checkingAccountRepository.save(formDto.toEntity());
        return new SimpleAccountDto(checkingAccount);
    }

    @Transactional
    public SimpleAccountDto updateAccount(Long id, CheckingAccountFormDto formDto) {
        CheckingAccount checkingAccount = checkingAccountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        checkingAccount.update(formDto);
        checkingAccountRepository.save(checkingAccount);
        return new SimpleAccountDto(checkingAccount);
    }


    @Transactional
    public void deleteCheckingAccount(Long id) {
        checkingAccountRepository.deleteById(id);
    }

    public boolean accountNotExists(Long id) {
        Optional<CheckingAccount> possibleAccount = checkingAccountRepository.findById(id);
        return possibleAccount.isEmpty();
    }

    public Optional<CheckingAccount> findAccountById(Long id) {
        return checkingAccountRepository.findById(id);
    }

    @Transactional
    public void transfer(Long id, TransferInfo transferInfo) {
        CheckingAccount account = checkingAccountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (transferInfo.getDestinationAccountType() == AccountType.CHECKING) {
            CheckingAccount destinationAccount = checkingAccountRepository.findById(transferInfo.getDestinationId())
                    .orElseThrow(EntityNotFoundException::new);
            account.transfer(transferInfo.getValue(), destinationAccount);
            checkingAccountRepository.save(account);
            checkingAccountRepository.save(destinationAccount);
        } else {
            SavingsAccount destinationAccount = savingsAccountRepository.findById(transferInfo.getDestinationId())
                    .orElseThrow(EntityNotFoundException::new);
            account.transfer(transferInfo.getValue(), destinationAccount);
            checkingAccountRepository.save(account);
            savingsAccountRepository.save(destinationAccount);
        }

    }

    @Transactional
    public void deposit(TransferInfo transferInfo) {
        CheckingAccount account = checkingAccountRepository.findById(transferInfo.getDestinationId())
                .orElseThrow(EntityNotFoundException::new);
        account.recieve(transferInfo.getValue());
        checkingAccountRepository.save(account);
    }
}

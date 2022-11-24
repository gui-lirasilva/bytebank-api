package br.com.byteBank.account.checkingAccount;

import br.com.byteBank.account.AccountType;
import br.com.byteBank.account.TransferInfo;
import br.com.byteBank.account.checkingAccount.dto.CheckingAccountDto;
import br.com.byteBank.account.checkingAccount.dto.CheckingAccountFormDto;
import br.com.byteBank.account.checkingAccount.dto.CheckingAccountSimpleDto;
import br.com.byteBank.account.savingsAccount.SavingsAccount;
import br.com.byteBank.account.savingsAccount.SavingsAccountRepository;
import br.com.byteBank.client.Client;
import br.com.byteBank.client.ClientRepository;
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
    private final ClientRepository clientRepository;

    public List<CheckingAccountSimpleDto> listAllCheckingAccounts(Pageable pageable) {
        return checkingAccountRepository.findAll(pageable).stream().map(CheckingAccountSimpleDto::new).toList();
    }

    @Transactional
    public CheckingAccountSimpleDto create(CheckingAccountFormDto formDto) {
        CheckingAccount checkingAccount = checkingAccountRepository.save(formDto.toEntity());
        return new CheckingAccountSimpleDto(checkingAccount);
    }

    @Transactional
    public CheckingAccountSimpleDto updateAccount(Long id, CheckingAccountFormDto formDto) {
        CheckingAccount checkingAccount = checkingAccountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        checkingAccount.update(formDto);
        checkingAccountRepository.save(checkingAccount);
        return new CheckingAccountSimpleDto(checkingAccount);
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
        if (transferInfo.getAccountType() == AccountType.CHECKING) {
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
}

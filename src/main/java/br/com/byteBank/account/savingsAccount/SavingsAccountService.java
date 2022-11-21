package br.com.byteBank.account.savingsAccount;

import br.com.byteBank.account.TransferInfo;
import br.com.byteBank.account.checkingAccount.CheckingAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SavingsAccountService {

    private final SavingsAccountRepository savingsAccountRepository;

    public List<SavingsAccountSimpleDto> listAllSavingsAccounts(Pageable pageable) {
        return savingsAccountRepository.findAll(pageable).stream().map(SavingsAccountSimpleDto::new).toList();
    }

    @Transactional
    public SavingsAccountSimpleDto create(SavingsAccountFormDto formDto) {
        SavingsAccount savingsAccount = savingsAccountRepository.save(formDto.toEntity());
        return new SavingsAccountSimpleDto(savingsAccount);
    }

    @Transactional
    public SavingsAccountSimpleDto updateAccount(Long id, SavingsAccountFormDto formDto) {
        SavingsAccount savingsAccount = savingsAccountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        savingsAccount.update(formDto);
        savingsAccountRepository.save(savingsAccount);
        return new SavingsAccountSimpleDto(savingsAccount);
    }

    @Transactional
    public void deleteSavingsAccount(Long id) {
        savingsAccountRepository.deleteById(id);
    }

    @Transactional
    public void transfer(Long id, TransferInfo transferInfo) {
        SavingsAccount account = savingsAccountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        SavingsAccount destinationAccount = savingsAccountRepository.findById(transferInfo.getDestinationId())
                .orElseThrow(EntityNotFoundException::new);
        account.transfer(transferInfo.getValue(), destinationAccount);
        savingsAccountRepository.save(account);
        savingsAccountRepository.save(destinationAccount);
    }
}

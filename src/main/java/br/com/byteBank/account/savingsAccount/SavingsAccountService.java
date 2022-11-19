package br.com.byteBank.account.savingsAccount;

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
}

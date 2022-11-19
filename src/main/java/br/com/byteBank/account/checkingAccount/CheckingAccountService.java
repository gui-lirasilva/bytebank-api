package br.com.byteBank.account.checkingAccount;

import br.com.byteBank.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckingAccountService {

    private final CheckingAccountRepository checkingAccountRepository;

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

    public CheckingAccountDto findCheckingAccountByClient(Client client) {
        CheckingAccount checkingAccount = checkingAccountRepository.findByClient(client)
                .orElseThrow(EntityNotFoundException::new);
        return new CheckingAccountDto(checkingAccount);
    }
}

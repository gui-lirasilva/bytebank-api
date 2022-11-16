package br.com.byteBank.account.checkingAccount;

import br.com.byteBank.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckingAccountService {

    private final CheckingAccountRepository checkingAccountRepository;

    public List<CheckingAccountDto> listAllCheckingAccounts(Pageable pageable) {
        return checkingAccountRepository.findAll(pageable).stream().map(CheckingAccountDto::new).toList();
    }

//    public Page<CheckingAccount> listAllCheckingAccounts(Pageable pageable) {
//        return checkingAccountRepository.findAll(pageable);
//    }

    public CheckingAccountDto insertCheckingAccount(@Valid CheckingAccountFormDto formDto) {
        CheckingAccount checkingAccount = checkingAccountRepository
                .save(new CheckingAccount(formDto.getClient(), formDto.getBalance()));
        return new CheckingAccountDto(checkingAccount);
    }

    public CheckingAccountDto findCheckingAccountByClient(Client client) {
        CheckingAccount checkingAccount = checkingAccountRepository.findByClient(client)
                .orElseThrow(EntityNotFoundException::new);
        return new CheckingAccountDto(checkingAccount);
    }

    public void deleteCheckingAccount(Long id) {
        checkingAccountRepository.deleteById(id);
    }


}

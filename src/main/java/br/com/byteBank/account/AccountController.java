package br.com.byteBank.account;

import br.com.byteBank.account.checkingAccount.CheckingAccountService;
import br.com.byteBank.account.savingsAccount.SavingsAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final CheckingAccountService checkingAccountService;
    private final SavingsAccountService savingsAccountService;

    @PutMapping("/deposit")
    public ResponseEntity<AccountSimpleDto> deposit(@RequestBody @Valid TransferInfo transferInfo) {
        if(transferInfo.getDestinationAccountType().equals(AccountType.CHECKING)) {
            checkingAccountService.deposit(transferInfo);
            AccountSimpleDto simpleDto = new AccountSimpleDto(checkingAccountService.findAccountById(transferInfo.getDestinationId())
                    .orElseThrow(EntityNotFoundException::new));
            return ResponseEntity.ok(simpleDto);
        }
        savingsAccountService.deposit(transferInfo);
        AccountSimpleDto simpleDto = new AccountSimpleDto(savingsAccountService.findAccountById(transferInfo.getDestinationId())
                .orElseThrow(EntityNotFoundException::new));
        return ResponseEntity.ok(simpleDto);
    }
}

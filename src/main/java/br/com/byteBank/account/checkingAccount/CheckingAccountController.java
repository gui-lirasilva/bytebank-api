package br.com.byteBank.account.checkingAccount;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/CheckingAccount")
@RequiredArgsConstructor
public class CheckingAccountController {

    private final CheckingAccountService checkingAccountService;

    @GetMapping
    public List<CheckingAccountDto> list(@PageableDefault(size = 10) Pageable pageable){
        return checkingAccountService.listAllCheckingAccounts(pageable);
    }

}


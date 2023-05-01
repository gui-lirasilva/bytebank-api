package br.com.byteBank.account.checkingAccount;

import br.com.byteBank.account.SimpleAccountDto;
import br.com.byteBank.account.AccountType;
import br.com.byteBank.account.TransferInfo;
import br.com.byteBank.account.checkingAccount.dto.CheckingAccountDto;
import br.com.byteBank.account.checkingAccount.dto.CheckingAccountFormDto;
import br.com.byteBank.client.Client;
import br.com.byteBank.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/account/checking")
@RequiredArgsConstructor
public class CheckingAccountController {

    private final CheckingAccountService checkingAccountService;
    private final ClientService clientService;

    @GetMapping
    public List<SimpleAccountDto> list(@PageableDefault(size = 10) Pageable pageable){
        return checkingAccountService.listAllCheckingAccounts(pageable);
    }

    @GetMapping("/detail/{id}")
    public CheckingAccountDto detail(@PathVariable @NotNull @Min(1) Long id) {
        CheckingAccount checkingAccount = checkingAccountService.findAccountById(id).orElseThrow(EntityNotFoundException::new);
        return new CheckingAccountDto(checkingAccount);
    }

    @PostMapping("/new")
    public ResponseEntity<SimpleAccountDto> create(@RequestBody @Valid CheckingAccountFormDto formDto,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        if(clientService.clientNotExists(formDto.getClientId())) {
            throw new IllegalArgumentException("The client not exists");
        }
        if (clientService.findCheckingAccount(formDto.getClientId()).isPresent()) {
            throw new IllegalArgumentException("This client already have an checking account");
        }
        Client client = clientService.findById(formDto.getClientId()).orElseThrow(EntityNotFoundException::new);
        formDto.setClient(client);
        SimpleAccountDto account = checkingAccountService.create(formDto);
        URI uri = uriComponentsBuilder.path("/account/checking/{id}").buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(uri).body(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleAccountDto> update(@PathVariable @NotNull @Min(1) Long id,
                                                   @RequestBody @Valid CheckingAccountFormDto formDto) {
        if(clientService.clientNotExists(formDto.getClientId())) {
            throw new IllegalArgumentException("The client not exists");
        }
        Client client = clientService.findById(formDto.getClientId()).orElseThrow(EntityNotFoundException::new);
        formDto.setClient(client);
        SimpleAccountDto account = checkingAccountService.updateAccount(id, formDto);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleAccountDto> delete(@PathVariable @NotNull @Min(1) Long id) {
        checkingAccountService.deleteCheckingAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/transfer")
    public ResponseEntity<SimpleAccountDto> transfer(@PathVariable @NotNull @Min(1) Long id,
                                                     @RequestBody @Valid TransferInfo transferInfo) {
        if(checkingAccountService.accountNotExists(id)) {
            throw new IllegalArgumentException("The account not exists");
        }

        CheckingAccount checkingAccount = checkingAccountService.findAccountById(id).get();

        if(transferInfo.getDestinationAccountType() == AccountType.CHECKING) {
            if(Objects.equals(checkingAccount.getId(), transferInfo.getDestinationId())) {
                throw new IllegalArgumentException("Illegal transaction for same account");
            }
        }
        checkingAccountService.transfer(id, transferInfo);
        SimpleAccountDto simpleDto = new SimpleAccountDto(checkingAccount);
        return ResponseEntity.ok(simpleDto);
    }

}


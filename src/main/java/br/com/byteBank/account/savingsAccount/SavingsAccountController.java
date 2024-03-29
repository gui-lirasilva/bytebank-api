package br.com.byteBank.account.savingsAccount;

import br.com.byteBank.account.AccountType;
import br.com.byteBank.account.SimpleAccountDto;
import br.com.byteBank.account.TransferInfo;
import br.com.byteBank.account.savingsAccount.dto.SavingsAccountDto;
import br.com.byteBank.account.savingsAccount.dto.SavingsAccountFormDto;
import br.com.byteBank.client.Client;
import br.com.byteBank.client.ClientService;
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
@RequestMapping("/account/savings")
public class SavingsAccountController {

    private final SavingsAccountService savingsAccountService;
    private final ClientService clientService;

    public SavingsAccountController(SavingsAccountService savingsAccountService, ClientService clientService) {
        this.savingsAccountService = savingsAccountService;
        this.clientService = clientService;
    }

    @GetMapping
    public List<SimpleAccountDto> list(@PageableDefault(size = 10) Pageable pageable) {
        return savingsAccountService.listAllSavingsAccounts(pageable);
    }

    @GetMapping("/detail/{id}")
    public SavingsAccountDto detail(@PathVariable @NotNull @Min(1) Long id) {
        SavingsAccount savingsAccount = savingsAccountService.findAccountById(id).orElseThrow(EntityNotFoundException::new);
        return new SavingsAccountDto(savingsAccount);
    }

    @PostMapping("/new")
    public ResponseEntity<SimpleAccountDto> create(@RequestBody @Valid SavingsAccountFormDto formDto,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        if (clientService.clientNotExists(formDto.getClientId())) {
            throw new IllegalArgumentException("The client not exists");
        }
        if (clientService.findSavingsAccount(formDto.getClientId()).isPresent()) {
            throw new IllegalArgumentException("This client already have an savings account");
        }
        Client client = clientService.findById(formDto.getClientId()).orElseThrow(EntityNotFoundException::new);
        formDto.setClient(client);
        SimpleAccountDto account = savingsAccountService.create(formDto);
        URI uri = uriComponentsBuilder.path("/account/savings/{id}").buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(uri).body(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleAccountDto> update(@PathVariable @NotNull @Min(1) Long id,
                                                   @RequestBody @Valid SavingsAccountFormDto formDto) {
        if (clientService.clientNotExists(formDto.getClientId())) {
            throw new IllegalArgumentException("The client not exists");
        }
        Client client = clientService.findById(formDto.getClientId()).orElseThrow(EntityNotFoundException::new);
        formDto.setClient(client);
        SimpleAccountDto account = savingsAccountService.updateAccount(id, formDto);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleAccountDto> delete(@PathVariable @NotNull @Min(1) Long id) {
        savingsAccountService.deleteSavingsAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/transfer")
    public ResponseEntity<SimpleAccountDto> transfer(@PathVariable @NotNull @Min(1) Long id,
                                                     @RequestBody @Valid TransferInfo transferInfo) {
        if (savingsAccountService.accountNotExists(id)) {
            throw new IllegalArgumentException("The account not exists");
        }

        SavingsAccount savingsAccount = savingsAccountService.findAccountById(id).get();

        if (transferInfo.getDestinationAccountType() == AccountType.SAVINGS) {
            if (Objects.equals(savingsAccount.getId(), transferInfo.getDestinationId())) {
                throw new IllegalArgumentException("Illegal transaction for same account");
            }
        }

        savingsAccountService.transfer(id, transferInfo);
        SimpleAccountDto simpleDto = new SimpleAccountDto(savingsAccount);
        return ResponseEntity.ok(simpleDto);
    }
}

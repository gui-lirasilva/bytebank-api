package br.com.byteBank.account.savingsAccount;

import br.com.byteBank.client.Client;
import br.com.byteBank.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/account/savings")
@RequiredArgsConstructor
public class SavingsAccountController {

    private final SavingsAccountService savingsAccountService;
    private final ClientService clientService;

    @GetMapping
    public List<SavingsAccountSimpleDto> list(@PageableDefault(size = 10) Pageable pageable){
        return savingsAccountService.listAllSavingsAccounts(pageable);
    }

    @PostMapping("/new")
    @Transactional
    public ResponseEntity<SavingsAccountSimpleDto> create(@RequestBody @Valid SavingsAccountFormDto formDto, UriComponentsBuilder uriComponentsBuilder) {
        if(clientService.clientNotExists(formDto.getClientId())) {
            throw new IllegalArgumentException("The client not exists");
        }
        Client client = clientService.findById(formDto.getClientId()).orElseThrow(EntityNotFoundException::new);
        formDto.setClient(client);
        SavingsAccountSimpleDto account = savingsAccountService.create(formDto);
        URI uri = uriComponentsBuilder.path("/account/savings/{id}").buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(uri).body(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SavingsAccountSimpleDto> update(@PathVariable @NotNull @Min(1) Long id, @RequestBody @Valid SavingsAccountFormDto formDto) {
        if(clientService.clientNotExists(formDto.getClientId())) {
            throw new IllegalArgumentException("The client not exists");
        }
        Client client = clientService.findById(formDto.getClientId()).orElseThrow(EntityNotFoundException::new);
        formDto.setClient(client);
        SavingsAccountSimpleDto account = savingsAccountService.updateAccount(id, formDto);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SavingsAccountSimpleDto> delete(@PathVariable @NotNull @Min(1) Long id) {
        savingsAccountService.deleteSavingsAccount(id);
        return ResponseEntity.noContent().build();
    }

}

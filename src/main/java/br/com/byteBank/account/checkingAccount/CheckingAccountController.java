package br.com.byteBank.account.checkingAccount;

import br.com.byteBank.client.Client;
import br.com.byteBank.client.ClientDto;
import br.com.byteBank.client.ClientFormDto;
import br.com.byteBank.client.ClientRepository;
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
@RequestMapping("/account/checking")
@RequiredArgsConstructor
public class CheckingAccountController {

    private final CheckingAccountService checkingAccountService;
    private final ClientRepository clientRepository;

    @GetMapping
    public List<CheckingAccountSimpleDto> list(@PageableDefault(size = 10) Pageable pageable){
        return checkingAccountService.listAllCheckingAccounts(pageable);
    }

    @PostMapping("/new")
    @Transactional
    public ResponseEntity<CheckingAccountSimpleDto> create(@RequestBody @Valid CheckingAccountFormDto formDto, UriComponentsBuilder uriComponentsBuilder) {
        if(clientRepository.findById(formDto.getClientId()).isEmpty()) {
            throw new IllegalArgumentException("The client not exists");
        }
        Client client = clientRepository.findById(formDto.getClientId()).get();
        formDto.setClient(client);
        CheckingAccountSimpleDto account = checkingAccountService.create(formDto);
        URI uri = uriComponentsBuilder.path("/account/checking/{id}").buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(uri).body(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckingAccountSimpleDto> update(@PathVariable @NotNull @Min(1) Long id, @RequestBody @Valid CheckingAccountFormDto formDto) {
        if(clientRepository.findById(formDto.getClientId()).isEmpty()) {
            throw new IllegalArgumentException("The client not exists");
        }
        formDto.setClient(clientRepository.findById(formDto.getClientId()).get());
        CheckingAccountSimpleDto account = checkingAccountService.updateAccount(id, formDto);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CheckingAccountSimpleDto> delete(@PathVariable @NotNull @Min(1) Long id) {
        checkingAccountService.deleteCheckingAccount(id);
        return ResponseEntity.noContent().build();
    }

}


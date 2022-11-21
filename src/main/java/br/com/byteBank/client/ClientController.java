package br.com.byteBank.client;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientDto> list(@PageableDefault(size = 10) Pageable pageable) {
        return clientService.listAllClients(pageable);
    }

    @PostMapping("/new")
    @Transactional
    public ResponseEntity<ClientDto> create(@RequestBody @Valid ClientFormDto formDto, UriComponentsBuilder uriComponentsBuilder) {
        ClientDto client = clientService.create(formDto);
        URI uri = uriComponentsBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(client);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClientDto> update(@PathVariable @NotNull @Min(1) Long id, @RequestBody @Valid ClientFormDto formDto) {
        ClientDto client = clientService.updateClient(id, formDto);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ClientDto> delete(@PathVariable @NotNull @Min(1) Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

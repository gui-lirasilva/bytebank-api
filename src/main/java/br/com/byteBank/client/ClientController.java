package br.com.byteBank.client;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<Client> create(@RequestBody @Valid ClientFormDto formDto, UriComponentsBuilder uriComponentsBuilder) {
        Client client = clientService.create(formDto);
        URI uri = uriComponentsBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(client);
    }
}

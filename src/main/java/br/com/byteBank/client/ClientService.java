package br.com.byteBank.client;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientDto> listAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable).stream().map(ClientDto::new).toList();
    }

    public ClientDto createDto(ClientFormDto formDto) {
        Client client = clientRepository.save(formDto.toEntity());
        return new ClientDto(client);
    }

    public Client create(ClientFormDto formDto) {
        Client client = clientRepository.save(formDto.toEntity());
        return client;
    }
}

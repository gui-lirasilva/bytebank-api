package br.com.byteBank.client;

import br.com.byteBank.account.checkingAccount.CheckingAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientDto> listAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable).stream().map(ClientDto::new).toList();
    }

    public boolean clientNotExists(Long id) {
        Optional<Client> possibleClient = clientRepository.findById(id);
        return possibleClient.isEmpty();
    }

    @Transactional
    public ClientDto create(ClientFormDto formDto) {
        if(clientRepository.findByCpf(formDto.getCpf()).isPresent()) {
            throw new IllegalArgumentException("The cpf has already utilized");
        }
        Client client = clientRepository.save(formDto.toEntity());
        return new ClientDto(client);
    }

    @Transactional
    public ClientDto updateClient(Long id, ClientFormDto formDto) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        client.update(formDto);
        clientRepository.save(client);
        return new ClientDto(client);
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public Optional<Client> findById(Long clientId) {
        return clientRepository.findById(clientId);
    }
}

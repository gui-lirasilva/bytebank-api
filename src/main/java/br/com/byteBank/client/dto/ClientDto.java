package br.com.byteBank.client.dto;

import br.com.byteBank.client.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id;
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDay;
    private String cpf;
    private String profession;
    private String address;

    @Email
    private String email;

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.birthDay = client.getBirthDay();
        this.cpf = client.getCpf();
        this.profession = client.getProfession();
        this.address = client.getAddress();
        this.email = client.getEmail();
    }
}

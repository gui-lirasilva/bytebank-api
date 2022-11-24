package br.com.byteBank.client;

import br.com.byteBank.client.dto.ClientFormDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate birthDay;

    @NotBlank
    private String cpf;
    private String profession;
    private String address;
    private String email;

    public Client(String name, LocalDate birthDay, String cpf, String profession, String address, String email) {
        this.name = name;
        this.birthDay = birthDay;
        this.cpf = cpf;
        this.profession = profession;
        this.address = address;
        this.email = email;
    }

    public void update(ClientFormDto formDto) {
        this.name = formDto.getName();
        this.birthDay = formDto.getBirthDay();
        this.cpf = formDto.getCpf();
        this.profession = formDto.getProfession();
        this.address = formDto.getAddress();
        this.email = formDto.getEmail();
    }
}

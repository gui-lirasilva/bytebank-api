package br.com.byteBank.client;

import br.com.byteBank.client.dto.ClientFormDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    public Client(Long id, @NotBlank String name, @NotNull LocalDate birthDay, @NotBlank String cpf, String profession, String address, String email) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.cpf = cpf;
        this.profession = profession;
        this.address = address;
        this.email = email;
    }

    public Client() {
    }

    public void update(ClientFormDto formDto) {
        this.name = formDto.getName();
        this.birthDay = formDto.getBirthDay();
        this.cpf = formDto.getCpf();
        this.profession = formDto.getProfession();
        this.address = formDto.getAddress();
        this.email = formDto.getEmail();
    }

    public Long getId() {
        return this.id;
    }

    public @NotBlank String getName() {
        return this.name;
    }

    public @NotNull LocalDate getBirthDay() {
        return this.birthDay;
    }

    public @NotBlank String getCpf() {
        return this.cpf;
    }

    public String getProfession() {
        return this.profession;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.email;
    }
}

package br.com.byteBank.client.dto;

import br.com.byteBank.client.Client;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import java.time.LocalDate;

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

    public ClientDto(Long id, String name, LocalDate birthDay, String cpf, String profession, String address, @Email String email) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.cpf = cpf;
        this.profession = profession;
        this.address = address;
        this.email = email;
    }

    public ClientDto() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getBirthDay() {
        return this.birthDay;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getProfession() {
        return this.profession;
    }

    public String getAddress() {
        return this.address;
    }

    public @Email String getEmail() {
        return this.email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonFormat(pattern = "dd/MM/yyyy")
    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }
}

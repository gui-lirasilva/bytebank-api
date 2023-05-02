package br.com.byteBank.client.dto;

import br.com.byteBank.client.Client;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class ClientFormDto {

    @NotBlank
    private String name;

    @PastOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDay;

    @NotBlank
    private String cpf;
    private String profession;
    private String address;

    @Email
    private String email;

    public ClientFormDto(@NotBlank String name, @PastOrPresent LocalDate birthDay, @NotBlank String cpf, String profession, String address, @Email String email) {
        this.name = name;
        this.birthDay = birthDay;
        this.cpf = cpf;
        this.profession = profession;
        this.address = address;
        this.email = email;
    }

    public ClientFormDto() {
    }

    public Client toEntity() {
        return new Client(name, birthDay, cpf, profession, address, email);
    }

    public @NotBlank String getName() {
        return this.name;
    }

    public @PastOrPresent LocalDate getBirthDay() {
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

    public @Email String getEmail() {
        return this.email;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    @JsonFormat(pattern = "dd/MM/yyyy")
    public void setBirthDay(@PastOrPresent LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public void setCpf(@NotBlank String cpf) {
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

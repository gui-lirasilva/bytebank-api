package br.com.byteBank.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public Client toEntity() {
        return new Client(name, birthDay, cpf, profession, address, email);
    }
}

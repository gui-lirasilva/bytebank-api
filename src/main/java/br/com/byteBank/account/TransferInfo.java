package br.com.byteBank.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferInfo {

    @NotNull
    @Min(1)
    private Long destinationId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private AccountType accountType;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal value;
}

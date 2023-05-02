package br.com.byteBank.account;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferInfo {

    @NotNull
    @Min(1)
    private Long destinationId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private AccountType destinationAccountType;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal value;

    public TransferInfo(@NotNull @Min(1) Long destinationId, @NotNull AccountType destinationAccountType, @NotNull @DecimalMin("0.0") BigDecimal value) {
        this.destinationId = destinationId;
        this.destinationAccountType = destinationAccountType;
        this.value = value;
    }

    public TransferInfo() {
    }

    public @NotNull @Min(1) Long getDestinationId() {
        return this.destinationId;
    }

    public @NotNull AccountType getDestinationAccountType() {
        return this.destinationAccountType;
    }

    public @NotNull @DecimalMin("0.0") BigDecimal getValue() {
        return this.value;
    }

    public void setDestinationId(@NotNull @Min(1) Long destinationId) {
        this.destinationId = destinationId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setDestinationAccountType(@NotNull AccountType destinationAccountType) {
        this.destinationAccountType = destinationAccountType;
    }

    public void setValue(@NotNull @DecimalMin("0.0") BigDecimal value) {
        this.value = value;
    }
}

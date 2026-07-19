package grameena.grameena_java_backend.dto.khatabook;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateTransactionalCropRequest {
    @NotNull(message = "fieldId is required")
    @Positive(message = "fieldId must be positive")
    private Integer fieldId;

    @NotNull(message = "cropId is required")
    @Positive(message = "cropId must be positive")
    private Integer cropId;

    @NotNull(message = "cropAcres is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "cropAcres must be greater than 0")
    private BigDecimal cropAcres;

    private LocalDate plantingDate;
}

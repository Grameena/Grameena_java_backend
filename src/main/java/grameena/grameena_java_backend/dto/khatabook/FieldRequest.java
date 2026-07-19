package grameena.grameena_java_backend.dto.khatabook;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FieldRequest {
    @NotBlank(message = "fieldName is required")
    @Size(max = 100, message = "fieldName must be at most 100 characters")
    private String fieldName;

    @NotNull(message = "acres is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "acres must be greater than 0")
    private BigDecimal acres;

    private Integer soilType;

    private Integer irrigationType;

    @NotBlank(message = "season is required")
    @Size(max = 50, message = "season must be at most 50 characters")
    private String season;
}

package grameena.grameena_java_backend.dto;





import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CompleteProfileRequest {
    private String username;
    @NotBlank(message = "username is required")
    @Size(min = 2, max = 50, message = "username must be between 2 and 50 characters")
private String phoneNumber;


    @NotNull(message = "age is required")
    @Min(value = 1, message = "age must be at least 1")
    @Max(value = 120, message = "age must be at most 120")
    private Integer age;

    @NotNull(message = "haveAgriculturalLand is required")
    private Boolean haveAgriculturalLand;

    @DecimalMin(value = "0.0", inclusive = true, message = "acres cannot be negative")
    private BigDecimal acres;
}
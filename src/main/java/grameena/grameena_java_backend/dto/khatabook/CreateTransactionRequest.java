package grameena.grameena_java_backend.dto.khatabook;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class CreateTransactionRequest {
    @NotNull(message = "transactionalCropId is required")
    @Positive(message = "transactionalCropId must be positive")
    private Integer transactionalCropId;

    private Boolean isExpense;

    @NotNull(message = "categoryId is required")
    @Positive(message = "categoryId must be positive")
    private Integer categoryId;

    @NotNull(message = "unitId is required")
    @Positive(message = "unitId must be positive")
    private Integer unitId;

    @DecimalMin(value = "0.0", inclusive = false, message = "quantity must be greater than 0")
    private BigDecimal quantity;

    @DecimalMin(value = "0.0", inclusive = true, message = "price cannot be negative")
    private BigDecimal price;

    @NotNull(message = "totalAmount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "totalAmount cannot be negative")
    private BigDecimal totalAmount;

    private OffsetDateTime transactionDate;

    @Size(max = 500, message = "notes must be at most 500 characters")
    private String notes;
}

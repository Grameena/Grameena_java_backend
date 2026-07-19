package grameena.grameena_java_backend.dto.khatabook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TransactionResponse {
    private Integer transactionId;
    private Integer transactionalCropId;
    private Boolean isExpense;
    private Integer categoryId;
    private Integer unitId;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal totalAmount;
    private OffsetDateTime transactionDate;
    private String notes;
    private OffsetDateTime createdAt;
}

package grameena.grameena_java_backend.dto.khatabook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CategoryTransactionDetailResponse {
    private Integer transactionId;
    private Integer transactionalCropId;
    private Integer fieldId;
    private String fieldName;
    private Integer cropId;
    private String cropName;
    private Integer unitId;
    private String unitName;
    private Boolean isExpense;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal totalAmount;
    private OffsetDateTime transactionDate;
    private String notes;
}

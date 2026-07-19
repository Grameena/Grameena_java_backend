package grameena.grameena_java_backend.repository.projection;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface CategoryTransactionDetailProjection {
    Integer getTransactionId();
    Integer getTransactionalCropId();
    Integer getFieldId();
    String getFieldName();
    Integer getCropId();
    String getCropName();
    Integer getCategoryId();
    String getCategoryName();
    Integer getUnitId();
    String getUnitName();
    Boolean getIsExpense();
    BigDecimal getQuantity();
    BigDecimal getPrice();
    BigDecimal getTotalAmount();
    OffsetDateTime getTransactionDate();
    String getNotes();
}

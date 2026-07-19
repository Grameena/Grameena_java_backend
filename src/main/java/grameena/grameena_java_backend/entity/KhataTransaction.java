package grameena.grameena_java_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhataTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "transactional_crop_id", nullable = false)
    private Integer transactionalCropId;

    @Column(name = "is_expense")
    private Boolean isExpense = true;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "unit_id", nullable = false)
    private Integer unitId;

    @Column(name = "quantity", precision = 10, scale = 2)
    private BigDecimal quantity;

    @Column(name = "price", precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "transaction_date")
    private OffsetDateTime transactionDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.transactionDate == null) {
            this.transactionDate = OffsetDateTime.now();
        }
        this.createdAt = OffsetDateTime.now();
    }
}

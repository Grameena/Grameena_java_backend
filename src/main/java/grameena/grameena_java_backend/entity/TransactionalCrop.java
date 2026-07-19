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
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "transactional_crops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionalCrop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactional_crop_id")
    private Integer transactionalCropId;

    @Column(name = "crop_id", nullable = false)
    private Integer cropId;

    @Column(name = "field_id", nullable = false)
    private Integer fieldId;

    @Column(name = "crop_acres", nullable = false, precision = 10, scale = 2)
    private BigDecimal cropAcres;

    @Column(name = "planting_date")
    private LocalDate plantingDate;

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }
}

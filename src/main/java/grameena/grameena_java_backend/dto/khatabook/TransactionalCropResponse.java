package grameena.grameena_java_backend.dto.khatabook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TransactionalCropResponse {
    private Integer transactionalCropId;
    private Integer fieldId;
    private Integer cropId;
    private BigDecimal cropAcres;
    private LocalDate plantingDate;
    private OffsetDateTime createdAt;
}

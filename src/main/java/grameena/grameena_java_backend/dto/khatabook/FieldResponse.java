package grameena.grameena_java_backend.dto.khatabook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
public class FieldResponse {
    private Integer fieldId;
    private String fieldName;
    private BigDecimal acres;
    private Integer soilType;
    private Integer irrigationType;
    private String season;
    private OffsetDateTime createdAt;
}

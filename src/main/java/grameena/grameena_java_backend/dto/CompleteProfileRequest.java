package grameena.grameena_java_backend.dto;





import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CompleteProfileRequest {
private String phoneNumber;
    private String username;
    private Integer age;
    private Boolean haveAgriculturalLand;
    private BigDecimal acres;
}
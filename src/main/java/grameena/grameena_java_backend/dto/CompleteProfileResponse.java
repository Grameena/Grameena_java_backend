
package grameena.grameena_java_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CompleteProfileResponse {

    private Long userId;
    private String phoneNumber;
    private String username;
    private Integer age;
    private Boolean haveAgriculturalLand;
    private BigDecimal acres;
    private Boolean isFirstLogin;
    private String token;
}
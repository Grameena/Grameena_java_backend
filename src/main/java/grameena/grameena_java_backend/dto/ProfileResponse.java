
package grameena.grameena_java_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProfileResponse {

    private String phoneNumber;
    private String username;
    private String email;
    private Integer age;
    private Boolean haveAgriculturalLand;
    private BigDecimal acres;
    private Integer roleId;
    private Boolean isFirstLogin;
}
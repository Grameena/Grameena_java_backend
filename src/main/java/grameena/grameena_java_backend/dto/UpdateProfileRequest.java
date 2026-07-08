


package grameena.grameena_java_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

    @Data
    public class UpdateProfileRequest {

        private String username;
        private String email;
        private Integer age;
        private Boolean haveAgriculturalLand;
        private BigDecimal acres;

    }


package grameena.grameena_java_backend.dto;
import lombok.*;
@Getter
@Setter

@AllArgsConstructor

public class VerifyOtpResponse {
    private Long userId;
    private Boolean isFirstLogin;
    private String token;
    }

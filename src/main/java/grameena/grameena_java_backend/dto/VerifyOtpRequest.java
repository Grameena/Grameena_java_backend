package grameena.grameena_java_backend.dto;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyOtpRequest {
    private String phoneNumber;
    private String otp;
}

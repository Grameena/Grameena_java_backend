package grameena.grameena_java_backend.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyOtpRequest {
    @NotBlank(message = "phoneNumber is required")
    @Pattern(regexp = "^\\d{10}$", message = "phoneNumber must be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "otp is required")
    @Size(min = 4, max = 4, message = "otp must be 4 digits")
    @Pattern(regexp = "^\\d{4}$", message = "otp must be numeric")
    private String otp;
}

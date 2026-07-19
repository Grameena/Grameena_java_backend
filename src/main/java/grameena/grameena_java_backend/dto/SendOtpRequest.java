package grameena.grameena_java_backend.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendOtpRequest {
    @NotBlank(message = "phoneNumber is required")
    @Pattern(regexp = "^\\d{10}$", message = "phoneNumber must be 10 digits")
    private String phoneNumber;
}

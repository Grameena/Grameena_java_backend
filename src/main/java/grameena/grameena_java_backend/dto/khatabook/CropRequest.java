package grameena.grameena_java_backend.dto.khatabook;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CropRequest {
    @NotBlank(message = "cropName is required")
    @Size(max = 100, message = "cropName must be at most 100 characters")
    private String cropName;

    @Positive(message = "cropImageId must be positive")
    private Integer cropImageId;
}

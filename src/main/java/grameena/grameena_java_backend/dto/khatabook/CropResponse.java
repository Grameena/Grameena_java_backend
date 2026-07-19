package grameena.grameena_java_backend.dto.khatabook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CropResponse {
    private Integer cropId;
    private String cropName;
    private Integer cropImageId;
}

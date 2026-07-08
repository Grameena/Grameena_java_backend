package grameena.grameena_java_backend.controller;
import org.springframework.web.bind.annotation.PutMapping;
import grameena.grameena_java_backend.dto.UpdateProfileRequest;
import grameena.grameena_java_backend.dto.ProfileResponse;
import grameena.grameena_java_backend.dto.CompleteProfileRequest;
import grameena.grameena_java_backend.dto.CompleteProfileResponse;
import grameena.grameena_java_backend.dto.ProfileResponse;
import grameena.grameena_java_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @SecurityRequirement(name = "Bearer Authentication")

    @PostMapping("/complete-profile")
    public ResponseEntity<CompleteProfileResponse> completeProfile(
            @RequestBody CompleteProfileRequest request
            ) {
        System.out.println("Inside Complete Profile Controller");
        String phoneNumber = request.getPhoneNumber();

        CompleteProfileResponse response =
                userService.completeProfile(phoneNumber, request);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/profile")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ProfileResponse> getProfile(
            Authentication authentication) {

        String phoneNumber = authentication.getName();

        ProfileResponse response = userService.getProfile(phoneNumber);

        return ResponseEntity.ok(response);
    }
    @PutMapping("/profile")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ProfileResponse> updateProfile(
            @RequestBody UpdateProfileRequest request,
            Authentication authentication) {

        String phoneNumber = authentication.getName();

        ProfileResponse response = userService.updateProfile(phoneNumber, request);

        return ResponseEntity.ok(response);
    }
    @PutMapping("/deactivate")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> deactivateProfile(Authentication authentication) {
        System.out.println("inside deactivate controller");

        String phoneNumber = authentication.getName();

        String response = userService.deactivateProfile(phoneNumber);

        return ResponseEntity.ok(response);
    }
}
package grameena.grameena_java_backend.controller;
import grameena.grameena_java_backend.dto.SendOtpRequest;
import grameena.grameena_java_backend.service.AuthService;
import grameena.grameena_java_backend.dto.VerifyOtpResponse;
import grameena.grameena_java_backend.dto.VerifyOtpRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



    @RestController
    @RequestMapping("/auth")
    public class AuthController {
        private final AuthService authService;
        public AuthController(AuthService authService) {
            this.authService = authService;
        }
        @PostMapping("/send-otp")
        public ResponseEntity<String> sendOtp(
            @Valid @RequestBody SendOtpRequest request) {

            authService.sendOtp(request);

            return ResponseEntity.ok("OTP Sent Successfully");
        }
        @PostMapping("/verify-otp")
        public ResponseEntity<VerifyOtpResponse> verifyOtp(
            @Valid @RequestBody VerifyOtpRequest request) {

            VerifyOtpResponse response = authService.verifyOtp(request);

            return ResponseEntity.ok(response);
        }

}

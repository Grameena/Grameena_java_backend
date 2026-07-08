package grameena.grameena_java_backend.service;
import grameena.grameena_java_backend.dto.VerifyOtpRequest;
import grameena.grameena_java_backend.dto.VerifyOtpResponse;
import  grameena.grameena_java_backend.dto.SendOtpRequest;
import grameena.grameena_java_backend.entity.User;
import grameena.grameena_java_backend.repository.UserRepository;
import grameena.grameena_java_backend.security.JwtService;
import org.springframework.stereotype.Service;
import lombok.*;

import java.util.Optional;

@Service
public class AuthService {

    public void sendOtp(SendOtpRequest request) {

        String otp = "1234";


    }

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public VerifyOtpResponse verifyOtp(VerifyOtpRequest request) {

        // Step 1: Verify OTP
        if (!"1234".equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        // Step 2: Check if user exists
        Optional<User> optionalUser =
                userRepository.findByPhoneNumber(request.getPhoneNumber());

        User user;

        if (optionalUser.isPresent()) {

            // Existing user
            user = optionalUser.get();

        } else {

            // New user
            user = new User();
user.setUsername(request.getPhoneNumber());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setIsFirstLogin(true);
           // user.setRoleId(2);
user = userRepository.save(user);
        }
        if (!user.getIsActive()) {
            throw new RuntimeException("Your account has been deactivated. Please contact the administrator.");
        }

        // Step 3: Generate JWT
        String token = null;
        if (!user.getIsFirstLogin()) {
            token = jwtService.generateToken(user);
        }

        // Step 4: Return response
        return new VerifyOtpResponse(
                user.getUserId(),
                user.getIsFirstLogin(),
                token
        );
    }
}
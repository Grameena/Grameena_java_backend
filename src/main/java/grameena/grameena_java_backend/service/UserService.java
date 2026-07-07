package grameena.grameena_java_backend.service;

import grameena.grameena_java_backend.dto.CompleteProfileRequest;
import grameena.grameena_java_backend.dto.CompleteProfileResponse;
import grameena.grameena_java_backend.dto.ProfileResponse;
import grameena.grameena_java_backend.entity.User;
import grameena.grameena_java_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public CompleteProfileResponse completeProfile(
            String phoneNumber,
            CompleteProfileRequest request) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(request.getUsername());
        user.setAge(request.getAge());
        user.setHaveAgriculturalLand(request.getHaveAgriculturalLand());
        user.setAcres(request.getAcres());
        user.setIsFirstLogin(false);
        user = userRepository.save(user);
        return new CompleteProfileResponse(
                user.getUserId(),
                user.getPhoneNumber(),
                user.getUsername(),
                user.getAge(),
                user.getHaveAgriculturalLand(),
                user.getAcres(),
                user.getIsFirstLogin()
        );
    }
    public ProfileResponse getProfile(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProfileResponse response = new ProfileResponse();

        response.setPhoneNumber(user.getPhoneNumber());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setAge(user.getAge());
        response.setHaveAgriculturalLand(user.getHaveAgriculturalLand());
        response.setAcres(user.getAcres());
        response.setRoleId(user.getRoleId());
        response.setIsFirstLogin(user.getIsFirstLogin());

        return response;
    }
}
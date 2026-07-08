package grameena.grameena_java_backend.service;

import grameena.grameena_java_backend.dto.CompleteProfileRequest;
import grameena.grameena_java_backend.dto.CompleteProfileResponse;
import grameena.grameena_java_backend.dto.ProfileResponse;
import grameena.grameena_java_backend.entity.User;
import grameena.grameena_java_backend.repository.UserRepository;
import grameena.grameena_java_backend.security.JwtService;
import org.springframework.stereotype.Service;
import grameena.grameena_java_backend.dto.UpdateProfileRequest;
import grameena.grameena_java_backend.dto.ProfileResponse;

import grameena.grameena_java_backend.entity.User;

import java.math.BigDecimal;

@Service
public class UserService {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository,JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService=jwtService;
    }
    public CompleteProfileResponse completeProfile(
            String phoneNumber,
            CompleteProfileRequest request) {
        User user = getUserByPhoneNumber(phoneNumber);
        updateUserFields(
                user,
                request.getUsername(),
                request.getAge(),
                request.getHaveAgriculturalLand(),
                request.getAcres()
        );
        user.setIsFirstLogin(false);

        user = userRepository.save(user);
        String token = jwtService.generateToken(user);

        return new CompleteProfileResponse(
                user.getUserId(),
                user.getPhoneNumber(),
                user.getUsername(),
                user.getAge(),
                user.getHaveAgriculturalLand(),
                user.getAcres(),

                user.getIsFirstLogin(),
                token
        );
    }
    public ProfileResponse getProfile(String phoneNumber) {
        User user = getUserByPhoneNumber(phoneNumber);
        return buildProfileResponse(user);
    }
    public ProfileResponse updateProfile(
            String phoneNumber,
            UpdateProfileRequest request){
        User user = getUserByPhoneNumber(phoneNumber);
        updateUserFields(
                user,
                request.getUsername(),
                request.getAge(),
                request.getHaveAgriculturalLand(),
                request.getAcres()
        );

        user.setEmail(request.getEmail());
        userRepository.save(user);
        return buildProfileResponse(user);
    }
    public String deactivateProfile(String phoneNumber) {

        User user = getUserByPhoneNumber(phoneNumber);
        user.setIsActive(false);
        user.setJwtVersion(user.getJwtVersion() + 1);

        userRepository.save(user);

        return "Profile deactivated successfully";
    }

    private User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private void updateUserFields(
            User user,
            String username,
            Integer age,
            Boolean haveAgriculturalLand,
            BigDecimal acres) {

        user.setUsername(username);
        user.setAge(age);
        user.setHaveAgriculturalLand(haveAgriculturalLand);
        user.setAcres(acres);
    }

    private ProfileResponse buildProfileResponse(User user) {

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
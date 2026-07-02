package grameena.grameena_java_backend.repository;
import grameena.grameena_java_backend.entity.User;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
}

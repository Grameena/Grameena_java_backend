package grameena.grameena_java_backend.repository;

import grameena.grameena_java_backend.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FieldRepository extends JpaRepository<FieldEntity, Integer> {
    List<FieldEntity> findByUserIdOrderByCreatedAtDesc(Long userId);
    Optional<FieldEntity> findByFieldIdAndUserId(Integer fieldId, Long userId);
}

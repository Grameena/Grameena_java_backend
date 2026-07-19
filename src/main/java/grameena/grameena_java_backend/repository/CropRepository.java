package grameena.grameena_java_backend.repository;

import grameena.grameena_java_backend.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CropRepository extends JpaRepository<Crop, Integer> {
    List<Crop> findAllByOrderByCropNameAsc();
    Optional<Crop> findByCropNameIgnoreCase(String cropName);
}

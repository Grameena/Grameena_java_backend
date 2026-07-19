package grameena.grameena_java_backend.repository;

import grameena.grameena_java_backend.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
    Optional<Unit> findByUnitIdAndCategoryId(Integer unitId, Integer categoryId);
}

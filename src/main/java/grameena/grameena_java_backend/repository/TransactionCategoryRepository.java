package grameena.grameena_java_backend.repository;

import grameena.grameena_java_backend.entity.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Integer> {
}

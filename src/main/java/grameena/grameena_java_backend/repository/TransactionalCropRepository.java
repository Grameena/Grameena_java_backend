package grameena.grameena_java_backend.repository;

import grameena.grameena_java_backend.entity.TransactionalCrop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionalCropRepository extends JpaRepository<TransactionalCrop, Integer> {

    @Query("""
            select case when count(tc) > 0 then true else false end
            from TransactionalCrop tc, FieldEntity f
            where tc.transactionalCropId = :transactionalCropId
            and tc.fieldId = f.fieldId
            and f.userId = :userId
            """)
    boolean existsByTransactionalCropIdAndUserId(
            @Param("transactionalCropId") Integer transactionalCropId,
            @Param("userId") Long userId
    );
}

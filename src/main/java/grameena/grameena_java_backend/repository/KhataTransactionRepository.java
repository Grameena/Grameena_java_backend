package grameena.grameena_java_backend.repository;

import grameena.grameena_java_backend.entity.KhataTransaction;
import grameena.grameena_java_backend.repository.projection.CategoryTransactionDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KhataTransactionRepository extends JpaRepository<KhataTransaction, Integer> {

    @Query(value = """
            SELECT
                t.transaction_id AS transactionId,
                tc.transactional_crop_id AS transactionalCropId,
                f.field_id AS fieldId,
                f.field_name AS fieldName,
                c.crop_id AS cropId,
                c.crop_name AS cropName,
                cat.transaction_category_id AS categoryId,
                cat.transaction_category_name AS categoryName,
                u.unit_id AS unitId,
                u.unit_name AS unitName,
                t.is_expense AS isExpense,
                t.quantity AS quantity,
                t.price AS price,
                t.total_amount AS totalAmount,
                t.transaction_date AS transactionDate,
                t.notes AS notes
            FROM transactions t
            INNER JOIN transactional_crops tc
                ON t.transactional_crop_id = tc.transactional_crop_id
            INNER JOIN fields f
                ON tc.field_id = f.field_id
            INNER JOIN crops c
                ON tc.crop_id = c.crop_id
            INNER JOIN transaction_categories cat
                ON t.category_id = cat.transaction_category_id
            INNER JOIN units u
                ON t.unit_id = u.unit_id
            WHERE f.user_id = :userId
            AND t.category_id = :categoryId
            ORDER BY t.transaction_date DESC, t.transaction_id DESC
            """, nativeQuery = true)
    List<CategoryTransactionDetailProjection> findDetailedByUserIdAndCategoryId(
            @Param("userId") Long userId,
            @Param("categoryId") Integer categoryId
    );
}

package grameena.grameena_java_backend.dto.khatabook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryTransactionsResponse {
    private Integer categoryId;
    private String categoryName;
    private BigDecimal totalExpense;
    private BigDecimal totalIncome;
    private BigDecimal netAmount;
    private List<CategoryTransactionDetailResponse> transactions;
}

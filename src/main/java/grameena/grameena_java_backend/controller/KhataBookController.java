package grameena.grameena_java_backend.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import grameena.grameena_java_backend.dto.khatabook.CategoryTransactionsResponse;
import grameena.grameena_java_backend.dto.khatabook.CreateTransactionRequest;
import grameena.grameena_java_backend.dto.khatabook.CreateTransactionalCropRequest;
import grameena.grameena_java_backend.dto.khatabook.CropRequest;
import grameena.grameena_java_backend.dto.khatabook.CropResponse;
import grameena.grameena_java_backend.dto.khatabook.FieldRequest;
import grameena.grameena_java_backend.dto.khatabook.FieldResponse;
import grameena.grameena_java_backend.dto.khatabook.TransactionResponse;
import grameena.grameena_java_backend.dto.khatabook.TransactionalCropResponse;
import grameena.grameena_java_backend.service.KhataBookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/khata-book")
@SecurityRequirement(name = "Bearer Authentication")
@Validated
public class KhataBookController {

    private final KhataBookService khataBookService;

    public KhataBookController(KhataBookService khataBookService) {
        this.khataBookService = khataBookService;
    }

    @PostMapping("/fields")
    public ResponseEntity<FieldResponse> createField(
            @Valid @RequestBody FieldRequest request,
            Authentication authentication
    ) {
        String phoneNumber = authentication.getName();
        return ResponseEntity.ok(khataBookService.createField(phoneNumber, request));
    }

    @GetMapping("/fields")
    public ResponseEntity<List<FieldResponse>> getMyFields(Authentication authentication) {
        String phoneNumber = authentication.getName();
        return ResponseEntity.ok(khataBookService.getUserFields(phoneNumber));
    }

    @PostMapping("/crops")
    public ResponseEntity<CropResponse> createCrop(@Valid @RequestBody CropRequest request) {
        return ResponseEntity.ok(khataBookService.createCrop(request));
    }

    @GetMapping("/crops")
    public ResponseEntity<List<CropResponse>> getAllCrops() {
        return ResponseEntity.ok(khataBookService.getAllCrops());
    }

    @PostMapping("/transactional-crops")
    public ResponseEntity<TransactionalCropResponse> createTransactionalCrop(
            @Valid @RequestBody CreateTransactionalCropRequest request,
            Authentication authentication
    ) {
        String phoneNumber = authentication.getName();
        return ResponseEntity.ok(khataBookService.createTransactionalCrop(phoneNumber, request));
    }

    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponse> createTransaction(
            @Valid @RequestBody CreateTransactionRequest request,
            Authentication authentication
    ) {
        String phoneNumber = authentication.getName();
        return ResponseEntity.ok(khataBookService.createTransaction(phoneNumber, request));
    }

    @GetMapping("/transactions/category/{categoryId}")
    public ResponseEntity<CategoryTransactionsResponse> getCategoryTransactions(
            @PathVariable @Positive(message = "categoryId must be positive") Integer categoryId,
            Authentication authentication
    ) {
        String phoneNumber = authentication.getName();
        return ResponseEntity.ok(khataBookService.getCategoryTransactions(phoneNumber, categoryId));
    }
}

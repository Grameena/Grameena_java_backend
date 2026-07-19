package grameena.grameena_java_backend.service;

import grameena.grameena_java_backend.dto.khatabook.CategoryTransactionDetailResponse;
import grameena.grameena_java_backend.dto.khatabook.CategoryTransactionsResponse;
import grameena.grameena_java_backend.dto.khatabook.CreateTransactionRequest;
import grameena.grameena_java_backend.dto.khatabook.CreateTransactionalCropRequest;
import grameena.grameena_java_backend.dto.khatabook.CropRequest;
import grameena.grameena_java_backend.dto.khatabook.CropResponse;
import grameena.grameena_java_backend.dto.khatabook.FieldRequest;
import grameena.grameena_java_backend.dto.khatabook.FieldResponse;
import grameena.grameena_java_backend.dto.khatabook.TransactionResponse;
import grameena.grameena_java_backend.dto.khatabook.TransactionalCropResponse;
import grameena.grameena_java_backend.entity.Crop;
import grameena.grameena_java_backend.entity.FieldEntity;
import grameena.grameena_java_backend.entity.KhataTransaction;
import grameena.grameena_java_backend.entity.TransactionCategory;
import grameena.grameena_java_backend.entity.TransactionalCrop;
import grameena.grameena_java_backend.entity.Unit;
import grameena.grameena_java_backend.entity.User;
import grameena.grameena_java_backend.exception.BadRequestException;
import grameena.grameena_java_backend.exception.ResourceNotFoundException;
import grameena.grameena_java_backend.repository.CropRepository;
import grameena.grameena_java_backend.repository.FieldRepository;
import grameena.grameena_java_backend.repository.KhataTransactionRepository;
import grameena.grameena_java_backend.repository.TransactionCategoryRepository;
import grameena.grameena_java_backend.repository.TransactionalCropRepository;
import grameena.grameena_java_backend.repository.UnitRepository;
import grameena.grameena_java_backend.repository.UserRepository;
import grameena.grameena_java_backend.repository.projection.CategoryTransactionDetailProjection;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class KhataBookService {

    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;
    private final CropRepository cropRepository;
    private final TransactionalCropRepository transactionalCropRepository;
    private final KhataTransactionRepository khataTransactionRepository;
    private final TransactionCategoryRepository transactionCategoryRepository;
    private final UnitRepository unitRepository;

    public KhataBookService(
            UserRepository userRepository,
            FieldRepository fieldRepository,
            CropRepository cropRepository,
            TransactionalCropRepository transactionalCropRepository,
            KhataTransactionRepository khataTransactionRepository,
            TransactionCategoryRepository transactionCategoryRepository,
            UnitRepository unitRepository
    ) {
        this.userRepository = userRepository;
        this.fieldRepository = fieldRepository;
        this.cropRepository = cropRepository;
        this.transactionalCropRepository = transactionalCropRepository;
        this.khataTransactionRepository = khataTransactionRepository;
        this.transactionCategoryRepository = transactionCategoryRepository;
        this.unitRepository = unitRepository;
    }

    public FieldResponse createField(String phoneNumber, FieldRequest request) {
        User user = getUserByPhoneNumber(phoneNumber);

        FieldEntity field = new FieldEntity();
        field.setUserId(user.getUserId());
        field.setFieldName(request.getFieldName());
        field.setAcres(request.getAcres());
        field.setSoilType(request.getSoilType());
        field.setIrrigationType(request.getIrrigationType());
        field.setSeason(request.getSeason());

        FieldEntity savedField = fieldRepository.save(field);

        return toFieldResponse(savedField);
    }

    public List<FieldResponse> getUserFields(String phoneNumber) {
        User user = getUserByPhoneNumber(phoneNumber);

        return fieldRepository.findByUserIdOrderByCreatedAtDesc(user.getUserId())
                .stream()
                .map(this::toFieldResponse)
                .toList();
    }

    public CropResponse createCrop(CropRequest request) {
        Crop crop = cropRepository.findByCropNameIgnoreCase(request.getCropName())
                .orElseGet(() -> {
                    Crop newCrop = new Crop();
                    newCrop.setCropName(request.getCropName());
                    newCrop.setCropImageId(request.getCropImageId());
                    return cropRepository.save(newCrop);
                });

        return new CropResponse(crop.getCropId(), crop.getCropName(), crop.getCropImageId());
    }

    public List<CropResponse> getAllCrops() {
        return cropRepository.findAllByOrderByCropNameAsc()
                .stream()
                .map(crop -> new CropResponse(crop.getCropId(), crop.getCropName(), crop.getCropImageId()))
                .toList();
    }

    public TransactionalCropResponse createTransactionalCrop(
            String phoneNumber,
            CreateTransactionalCropRequest request
    ) {
        User user = getUserByPhoneNumber(phoneNumber);

        fieldRepository.findByFieldIdAndUserId(request.getFieldId(), user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Field not found for logged in user"));

        cropRepository.findById(request.getCropId())
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found"));

        TransactionalCrop transactionalCrop = new TransactionalCrop();
        transactionalCrop.setFieldId(request.getFieldId());
        transactionalCrop.setCropId(request.getCropId());
        transactionalCrop.setCropAcres(request.getCropAcres());
        transactionalCrop.setPlantingDate(request.getPlantingDate());

        TransactionalCrop saved = transactionalCropRepository.save(transactionalCrop);

        return new TransactionalCropResponse(
                saved.getTransactionalCropId(),
                saved.getFieldId(),
                saved.getCropId(),
                saved.getCropAcres(),
                saved.getPlantingDate(),
                saved.getCreatedAt()
        );
    }

    public TransactionResponse createTransaction(String phoneNumber, CreateTransactionRequest request) {
        User user = getUserByPhoneNumber(phoneNumber);

        boolean transactionalCropBelongsToUser =
                transactionalCropRepository.existsByTransactionalCropIdAndUserId(
                        request.getTransactionalCropId(),
                        user.getUserId()
                );

        if (!transactionalCropBelongsToUser) {
            throw new ResourceNotFoundException("Transactional crop not found for logged in user");
        }

        TransactionCategory category = transactionCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction category not found"));

        Unit unit = unitRepository
                .findByUnitIdAndCategoryId(request.getUnitId(), request.getCategoryId())
                .orElseThrow(() -> new BadRequestException("Unit is not valid for selected category"));

        KhataTransaction transaction = new KhataTransaction();
        transaction.setTransactionalCropId(request.getTransactionalCropId());
        transaction.setIsExpense(request.getIsExpense() == null ? Boolean.TRUE : request.getIsExpense());
        transaction.setCategoryId(category.getTransactionCategoryId());
        transaction.setUnitId(unit.getUnitId());
        transaction.setQuantity(request.getQuantity());
        transaction.setPrice(request.getPrice());
        transaction.setTotalAmount(request.getTotalAmount());
        transaction.setTransactionDate(request.getTransactionDate());
        transaction.setNotes(request.getNotes());

        KhataTransaction saved = khataTransactionRepository.save(transaction);

        return new TransactionResponse(
                saved.getTransactionId(),
                saved.getTransactionalCropId(),
                saved.getIsExpense(),
                saved.getCategoryId(),
                saved.getUnitId(),
                saved.getQuantity(),
                saved.getPrice(),
                saved.getTotalAmount(),
                saved.getTransactionDate(),
                saved.getNotes(),
                saved.getCreatedAt()
        );
    }

    public CategoryTransactionsResponse getCategoryTransactions(String phoneNumber, Integer categoryId) {
        User user = getUserByPhoneNumber(phoneNumber);

        List<CategoryTransactionDetailProjection> details =
                khataTransactionRepository.findDetailedByUserIdAndCategoryId(user.getUserId(), categoryId);

        String categoryName;
        if (details.isEmpty()) {
            categoryName = transactionCategoryRepository.findById(categoryId)
                    .map(TransactionCategory::getTransactionCategoryName)
                                        .orElseThrow(() -> new ResourceNotFoundException("Transaction category not found"));
                } else {
                        categoryName = details.get(0).getCategoryName();
        }

        List<CategoryTransactionDetailResponse> transactions = details.stream()
                .map(detail -> new CategoryTransactionDetailResponse(
                        detail.getTransactionId(),
                        detail.getTransactionalCropId(),
                        detail.getFieldId(),
                        detail.getFieldName(),
                        detail.getCropId(),
                        detail.getCropName(),
                        detail.getUnitId(),
                        detail.getUnitName(),
                        detail.getIsExpense(),
                        detail.getQuantity(),
                        detail.getPrice(),
                        detail.getTotalAmount(),
                        detail.getTransactionDate(),
                        detail.getNotes()
                ))
                .toList();

        BigDecimal totalExpense = transactions.stream()
                .filter(item -> Boolean.TRUE.equals(item.getIsExpense()))
                .map(CategoryTransactionDetailResponse::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalIncome = transactions.stream()
                .filter(item -> Boolean.FALSE.equals(item.getIsExpense()))
                .map(CategoryTransactionDetailResponse::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal netAmount = totalIncome.subtract(totalExpense);

        return new CategoryTransactionsResponse(
                categoryId,
                categoryName,
                totalExpense,
                totalIncome,
                netAmount,
                transactions
        );
    }

    private User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private FieldResponse toFieldResponse(FieldEntity field) {
        return new FieldResponse(
                field.getFieldId(),
                field.getFieldName(),
                field.getAcres(),
                field.getSoilType(),
                field.getIrrigationType(),
                field.getSeason(),
                field.getCreatedAt()
        );
    }
}

package grameena.grameena_java_backend.entity;

import jakarta.persistence.*;

import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private Long userId;

        @Column(name = "phone_number", nullable = false, unique = true, length = 15)
        private String phoneNumber;

        @Column(nullable = false, length = 50)
        private String username;

        @Column(length = 100)
        private String email;

        @Column
        private Integer age;

        @Column(name = "is_first_login")
        private Boolean isFirstLogin = true;

        @Column(name = "have_agricultural_land")
        private Boolean haveAgriculturalLand = false;

        @Column(precision = 10, scale = 2)
        private BigDecimal acres;

        // Authentication Fields
@Column(name="is_active")
private Boolean isActive=true;
    @Column(name = "role_id")
private Integer roleId;
    @Column(name = "jwt_version")
    private Integer jwtVersion = 0;
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

    // Authentication fields


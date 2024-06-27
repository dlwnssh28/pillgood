package kr.co.pillgood.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import kr.co.pillgood.config.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @Column(name = "member_unique_id", columnDefinition = "CHAR(32)", nullable = false)
    private String memberUniqueId;

    @Column(name = "email", nullable = false, length = 100, unique = true, updatable = false)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "age" , nullable = false)
    private Integer age;

    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "subscription_status")
    private Boolean subscriptionStatus;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "member_level", length = 10, columnDefinition = "VARCHAR(10) DEFAULT 'USER'")
    @Enumerated(EnumType.STRING)
    private Role memberLevel;

    @PrePersist
    protected void onCreate() {
        if (this.memberUniqueId == null) {
            this.memberUniqueId = UUID.randomUUID().toString().replace("-", "");
        }
    }
}

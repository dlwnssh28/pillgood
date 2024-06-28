package com.pillgood.entity;

import com.pillgood.config.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

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
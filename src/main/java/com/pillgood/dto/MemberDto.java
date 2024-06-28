package com.pillgood.dto;

import java.time.LocalDateTime;

import com.pillgood.config.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private String memberUniqueId;
    private String email;
    private String password;
    private String name;
    private Integer age;
    private String gender;
    private String phoneNumber;
    private LocalDateTime registrationDate;
    private Boolean subscriptionStatus;
    private LocalDateTime modifiedDate;
    private Role memberLevel; // 수정 불가능 필드

    // 매개변수가 있는 생성자
    public MemberDto(String email) {
        this.email = email;
    }

    public MemberDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

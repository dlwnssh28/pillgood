package kr.co.pillgood.dto;

import java.time.LocalDateTime;

import kr.co.pillgood.config.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private String memberUniqueId;
    private String email; // 수정 불가능 필드
    private String password;
    private String name;
    private Integer age;
    private String gender;
    private String phoneNumber;
    private LocalDateTime registrationDate;
    private Boolean subscriptionStatus;
    private LocalDateTime modifiedDate;
    private Role memberLevel; // 수정 불가능 필드

    public void setMemberUniqueId(String memberUniqueId) {
        this.memberUniqueId = memberUniqueId;
    }
}

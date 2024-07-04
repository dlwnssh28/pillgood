package com.pillgood.controller;

import com.pillgood.dto.MemberDto;
import com.pillgood.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public MemberDto createMember(@RequestBody MemberDto memberDto) {
        System.out.println("createMember called with: " + memberDto);  // 디버깅용 로그
        return memberService.createMember(memberDto);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> getUserInfo(HttpSession session) {
        String memberId = (String) session.getAttribute("memberId");
        if (memberId != null) {
            // DB에서 사용자 정보 조회
            Optional<MemberDto> memberOpt = memberService.findById(memberId);
            if (memberOpt.isPresent()) {
                MemberDto member = memberOpt.get();
//                System.out.println("세션 확인: 사용자 ID - " + member.getMemberUniqueId());
//                System.out.println("세션 확인: 사용자 level - " + member.getMemberLevel());
                return ResponseEntity.ok(Collections.singletonMap("user", member));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid session");
            }
        } else {
            System.out.println("세션 확인: 세션이 없습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No active session");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody MemberDto memberDto, HttpSession session) {
//        System.out.println("입력 MemberDto: " + memberDto);

        // 이메일로 회원을 찾습니다.
        Optional<MemberDto> optionalMember = memberService.findByEmail(memberDto.getEmail());
//        System.out.println("optionalMember : " + optionalMember);

        if (optionalMember.isPresent()) {
            MemberDto foundMember = optionalMember.get();
//            System.out.println("멤버 검색 결과: " + foundMember);

            // 비밀번호 일치 여부 확인
            if (memberService.checkPassword(memberDto.getPassword(), foundMember.getPassword())) {
                System.out.println("비밀번호 확인: 로그인 성공");

                // 세션에 회원 정보 저장
                session.setAttribute("memberId", foundMember.getMemberUniqueId());
//                System.out.println("세션 사용자 아이디: " + foundMember.getMemberUniqueId());

                return ResponseEntity.ok("Login successful");
            } else {
                System.out.println("비밀번호 확인: 로그인 실패 - 비밀번호가 일치하지 않습니다.");

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } else {
            System.out.println("멤버가 존재하지 않습니다.");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/check-session")
    public ResponseEntity<?> checkSession(HttpSession session) {
//        System.out.println("세션 확인 요청: " + session.getId());
        String memberId = (String) session.getAttribute("memberId");
//        System.out.println("세션에서 가져온 memberId: " + memberId);
        if (memberId != null) {
            Optional<MemberDto> memberOpt = memberService.findById(memberId);
            if (memberOpt.isPresent()) {
                MemberDto member = memberOpt.get();
                // 필요한 최소한의 정보만 반환
                MemberDto responseDto = new MemberDto();
                responseDto.setMemberUniqueId(member.getMemberUniqueId());
//                System.out.println("세션 사용 중인 id : " + responseDto);
//                System.out.println("memberId : " + memberId);
                return ResponseEntity.ok(Collections.singletonMap("user", responseDto));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid session");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No active session");
        }
    }

    @GetMapping("/list")
    public List<MemberDto> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable String id, @RequestBody MemberDto memberDto) {
        Optional<MemberDto> updatedMember = memberService.updateMember(id, memberDto);
        return updatedMember
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteMember(@PathVariable String id) {
        return memberService.deleteMember(id);
    }

    @GetMapping("/findByEmail/{email}")
    public Optional<MemberDto> findByEmail(@PathVariable String email) {
        return memberService.findByEmail(email);
    }

    
 // 로그아웃 엔드포인트 추가
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        System.out.println("로그아웃: 세션 무효화");
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/mypage")
    public ResponseEntity<?> getUserProfile(HttpSession session) {
        String memberId = (String) session.getAttribute("memberId");
        if (memberId != null) {
            Optional<MemberDto> memberOpt = memberService.findById(memberId);
            if (memberOpt.isPresent()) {
                MemberDto member = memberOpt.get();
                return ResponseEntity.ok(member);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid session");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No active session");
        }
    }

    @PostMapping("/verifyPassword")
    public ResponseEntity<?> verifyPassword(@RequestBody Map<String, String> request) {
        String memberId = request.get("memberId");
        String password = request.get("password");

        Optional<MemberDto> optionalMember = memberService.findById(memberId);
        if (optionalMember.isPresent()) {
            MemberDto foundMember = optionalMember.get();
            if (memberService.checkPassword(password, foundMember.getPassword())) {
                return ResponseEntity.ok("Password verified");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid member");
        }
    }

}
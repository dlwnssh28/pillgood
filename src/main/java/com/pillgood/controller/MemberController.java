package com.pillgood.controller;

import com.pillgood.dto.MemberDto;
import com.pillgood.entity.Member;
import com.pillgood.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/register")
    public MemberDto createMember(@RequestBody MemberDto memberDto) {
        System.out.println("createMember called with: " + memberDto);  // 디버깅용 로그
        return memberService.createMember(memberDto);
    }

    @GetMapping("/find/{id}")
    public Optional<MemberDto> getMemberById(@PathVariable String id) {
        return memberService.getMemberById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody MemberDto memberDto, HttpSession session) {
        System.out.println("입력 MemberDto: " + memberDto);

        // 이메일로 회원을 찾습니다.
        Optional<MemberDto> optionalMember = memberService.findByEmail(memberDto.getEmail());
        System.out.println("optionalMember : " + optionalMember);

        if (optionalMember.isPresent()) {
            MemberDto foundMember = optionalMember.get();
            System.out.println("멤버 검색 결과: " + foundMember);

            // 비밀번호 일치 여부 확인
            if (memberService.checkPassword(memberDto.getPassword(), foundMember.getPassword())) {
                System.out.println("비밀번호 확인: 로그인 성공");

                // 세션에 회원 정보 저장
                session.setAttribute("member", foundMember);
                System.out.println("세션 사용자 이메일: " + foundMember.getEmail());

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

    @GetMapping("/list")
    public List<MemberDto> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PutMapping("/update/{id}")
    public Optional<MemberDto> updateMember(@PathVariable String id, @RequestBody MemberDto memberDto) {
        return memberService.updateMember(id, memberDto);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteMember(@PathVariable String id) {
        return memberService.deleteMember(id);
    }

    @GetMapping("/findByEmail/{email}")
    public Optional<MemberDto> findByEmail(@PathVariable String email) {
        return memberService.findByEmail(email);
    }
    }

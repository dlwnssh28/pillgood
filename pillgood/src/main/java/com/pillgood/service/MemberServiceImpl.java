package com.pillgood.service;

import com.pillgood.config.Role;
import com.pillgood.dto.MemberDto;
import com.pillgood.entity.Member;
import com.pillgood.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public Optional<MemberDto> findById(String memberId) {
        return memberRepository.findById(memberId)
                .map(member -> {
                    MemberDto memberDto = new MemberDto();
                    memberDto.setEmail(member.getEmail());
                    memberDto.setPassword(member.getPassword());
                    memberDto.setMemberUniqueId(member.getMemberUniqueId());
                    memberDto.setName(member.getName());
                    memberDto.setAge(member.getAge());
                    memberDto.setGender(member.getGender());
                    memberDto.setPhoneNumber(member.getPhoneNumber());
                    memberDto.setRegistrationDate(member.getRegistrationDate());
                    memberDto.setSubscriptionStatus(member.getSubscriptionStatus());
                    memberDto.setModifiedDate(member.getModifiedDate());
                    memberDto.setMemberLevel(member.getMemberLevel());
                    return memberDto;
                });
    }

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = convertToEntity(memberDto);
        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        member.setMemberLevel(Role.USER);
        member = memberRepository.save(member);
        return convertToDto(member);
    }

    @Override
    public Optional<MemberDto> getMemberById(String id) {
        return memberRepository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    public List<MemberDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public Optional<MemberDto> updateMember(String id, MemberDto memberDto) {
//        return memberRepository.findById(id)
//                .map(member -> {
//                    member.setEmail(memberDto.getEmail());
//                    if (!memberDto.getPassword().isEmpty()) {
//                        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
//                    }
//                    member.setName(memberDto.getName());
//                    member.setAge(memberDto.getAge());
//                    member.setGender(memberDto.getGender());
//                    member.setPhoneNumber(memberDto.getPhoneNumber());
//                    member.setRegistrationDate(memberDto.getRegistrationDate());
//                    member.setSubscriptionStatus(memberDto.getSubscriptionStatus());
//                    member.setMemberLevel(memberDto.getMemberLevel());
//                    member.setModifiedDate(memberDto.getModifiedDate());
//                    member = memberRepository.save(member);
//                    return convertToDto(member);
//                });
//    }

    @Override
    public Optional<MemberDto> updateMember(String id, MemberDto memberDto) {
        return memberRepository.findById(id)
                .map(existingMember -> {
                    Member updatedMember = convertToEntity(memberDto);
                    updatedMember.setMemberUniqueId(existingMember.getMemberUniqueId());
                    updatedMember.setPassword(existingMember.getPassword()); // 기존 비밀번호 유지
                    if (!memberDto.getPassword().isEmpty()) {
//                        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
                    }
                    updatedMember = memberRepository.save(updatedMember);
                    return convertToDto(updatedMember);
                });
    }
  
//    public Optional<MemberDto> updateMember(String id, MemberDto memberDto) {
//        return memberRepository.findById(id)
//                .map(existingMember -> {
////                    // 비밀번호 처리
////                    if (memberDto.getPassword() != null && !memberDto.getPassword().isEmpty()) {
////                        // 새로운 비밀번호가 제공된 경우에만 암호화하여 저장
////                        existingMember.setPassword(passwordEncoder.encode(memberDto.getPassword()));
////                    } else {
////                        // 비밀번호가 비어 있으면 기존 비밀번호 유지
////                        memberDto.setPassword(existingMember.getPassword());
////                    }
//
//                    // 다른 필드를 업데이트
//                    existingMember.setEmail(memberDto.getEmail() != null ? memberDto.getEmail() : existingMember.getEmail());
//                    existingMember.setName(memberDto.getName() != null ? memberDto.getName() : existingMember.getName());
//                    existingMember.setAge(memberDto.getAge() != null ? memberDto.getAge() : existingMember.getAge());
//                    existingMember.setGender(memberDto.getGender() != null ? memberDto.getGender() : existingMember.getGender());
//                    existingMember.setPhoneNumber(memberDto.getPhoneNumber() != null ? memberDto.getPhoneNumber() : existingMember.getPhoneNumber());
//                    existingMember.setSubscriptionStatus(memberDto.getSubscriptionStatus() != null ? memberDto.getSubscriptionStatus() : existingMember.getSubscriptionStatus());
//                    existingMember.setRegistrationDate(existingMember.getRegistrationDate());
//
//                    Member updatedMember = memberRepository.save(existingMember);
//                    return convertToDto(updatedMember);
//                });
//    }


    @Override
    public boolean deleteMember(String id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<MemberDto> findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(member -> {
                    MemberDto memberDto = new MemberDto();
                    memberDto.setEmail(member.getEmail());
                    memberDto.setPassword(member.getPassword());
                    memberDto.setMemberUniqueId(member.getMemberUniqueId());
                    return memberDto;
                });
    }

    private MemberDto convertToDto(Member member) {
        return new MemberDto(
                member.getMemberUniqueId(),
                member.getEmail(),
                member.getPassword(),
                member.getName(),
                member.getAge(),
                member.getGender(),
                member.getPhoneNumber(),
                member.getRegistrationDate(),
                member.getSubscriptionStatus(),
                member.getModifiedDate(),
                member.getMemberLevel()
        );
    }

    private Member convertToEntity(MemberDto memberDto) {
        Member member = new Member();
        member.setMemberUniqueId(memberDto.getMemberUniqueId() != null ? memberDto.getMemberUniqueId() : UUID.randomUUID().toString().replace("-", ""));
        member.setEmail(memberDto.getEmail());
        member.setPassword(memberDto.getPassword());
        member.setName(memberDto.getName());
        member.setAge(memberDto.getAge());
        member.setGender(memberDto.getGender());
        member.setPhoneNumber(memberDto.getPhoneNumber());
        member.setRegistrationDate(memberDto.getRegistrationDate());
        member.setSubscriptionStatus(memberDto.getSubscriptionStatus());
        member.setModifiedDate(memberDto.getModifiedDate());
        member.setMemberLevel(memberDto.getMemberLevel());
        return member;
    }
}
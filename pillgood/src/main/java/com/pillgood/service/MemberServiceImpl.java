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

    @Override
    public Optional<MemberDto> updateMember(String id, MemberDto memberDto) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setEmail(memberDto.getEmail());
                    if (!memberDto.getPassword().isEmpty()) {
                        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
                    }
                    member.setName(memberDto.getName());
                    member.setAge(memberDto.getAge());
                    member.setGender(memberDto.getGender());
                    member.setPhoneNumber(memberDto.getPhoneNumber());
                    member.setRegistrationDate(memberDto.getRegistrationDate());
                    member.setSubscriptionStatus(memberDto.getSubscriptionStatus());
                    member.setMemberLevel(memberDto.getMemberLevel());
                    member.setModifiedDate(memberDto.getModifiedDate());
                    member = memberRepository.save(member);
                    return convertToDto(member);
                });
    }

    @Override
    public boolean deleteMember(String id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }

//    @Override
//    public Optional<MemberDto> findByEmail(String email) {
//        return memberRepository.findByEmail(email)
//                .map(this::convertToDto);
//    }

    @Override
    public Optional<MemberDto> findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(member -> {
                    MemberDto memberDto = new MemberDto();
                    memberDto.setEmail(member.getEmail());
                    memberDto.setPassword(member.getPassword());
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

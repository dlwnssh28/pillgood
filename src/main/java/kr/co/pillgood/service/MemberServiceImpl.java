package kr.co.pillgood.service;

import kr.co.pillgood.dto.MemberDto;
import kr.co.pillgood.entity.Member;
import kr.co.pillgood.repository.MemberRepository;
import kr.co.pillgood.config.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    @Override
    public List<MemberDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        validateMemberDto(memberDto);

        // Check for duplicate memberId
        if (memberRepository.existsByEmail(memberDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Member member = convertToEntity(memberDto);
        member.setMemberUniqueId(UUID.randomUUID().toString().replace("-", ""));
        member.setRegistrationDate(LocalDateTime.now());
        member.setModifiedDate(LocalDateTime.now());
        member.setMemberLevel(Role.USER); // 기본 멤버 레벨을 USER로 설정
        Member savedMember = memberRepository.save(member);
        return convertToDto(savedMember);
    }

    @Override
    public Optional<MemberDto> getMemberById(String memberUniqueId) {
        return memberRepository.findById(memberUniqueId)
                .map(this::convertToDto);
    }

    @Override
    public MemberDto updateMember(String memberUniqueId, MemberDto memberDto) {
        validateMemberDto(memberDto);
        Member member = memberRepository.findById(memberUniqueId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        member.setPassword(memberDto.getPassword());
        member.setName(memberDto.getName());
        member.setAge(memberDto.getAge());
        member.setGender(memberDto.getGender());
        member.setEmail(memberDto.getEmail());
        member.setPhoneNumber(memberDto.getPhoneNumber());
        member.setSubscriptionStatus(memberDto.getSubscriptionStatus());
        member.setModifiedDate(LocalDateTime.now());

        Member updatedMember = memberRepository.save(member);
        return convertToDto(updatedMember);
    }

    @Override
    public void deleteMember(String memberUniqueId) {
        memberRepository.deleteById(memberUniqueId);
    }

    private MemberDto convertToDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberUniqueId(member.getMemberUniqueId());
        memberDto.setEmail(member.getEmail());
        memberDto.setPassword(member.getPassword());
        memberDto.setName(member.getName());
        memberDto.setAge(member.getAge());
        memberDto.setGender(member.getGender());
        memberDto.setPhoneNumber(member.getPhoneNumber());
        memberDto.setRegistrationDate(member.getRegistrationDate());
        memberDto.setSubscriptionStatus(member.getSubscriptionStatus());
        memberDto.setModifiedDate(member.getModifiedDate());
        memberDto.setMemberLevel(member.getMemberLevel());
        return memberDto;
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

    private void validateMemberDto(MemberDto memberDto) {
        if (memberDto.getName() == null || memberDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (memberDto.getPassword() == null || memberDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (memberDto.getEmail() == null || memberDto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
    }
}

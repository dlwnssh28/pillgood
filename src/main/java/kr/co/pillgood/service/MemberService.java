package kr.co.pillgood.service;

import kr.co.pillgood.dto.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<MemberDto> getAllMembers();
    MemberDto createMember(MemberDto memberDto);
    Optional<MemberDto> getMemberById(String memberUniqueId);
    MemberDto updateMember(String memberUniqueId, MemberDto memberDto);
    void deleteMember(String memberUniqueId);
}

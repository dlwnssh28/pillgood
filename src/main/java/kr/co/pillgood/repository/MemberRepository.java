package kr.co.pillgood.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.pillgood.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    boolean existsByEmail(String Email);
}
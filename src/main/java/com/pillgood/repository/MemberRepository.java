package com.pillgood.repository;

import com.pillgood.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
}

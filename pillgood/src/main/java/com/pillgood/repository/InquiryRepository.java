package com.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pillgood.entity.Inquiry;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {
}

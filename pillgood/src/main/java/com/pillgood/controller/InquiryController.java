package com.pillgood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.dto.InquiryDto;
import com.pillgood.service.InquiryService;

@RestController
@RequestMapping("/inquiries")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @GetMapping("/list")
    public List<InquiryDto> getAllInquiries() {
        return inquiryService.getAllInquiries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InquiryDto> getInquiryById(@PathVariable int id) {
        InquiryDto inquiryDto = inquiryService.getInquiryById(id);
        if (inquiryDto != null) {
            return ResponseEntity.ok(inquiryDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<InquiryDto> createInquiry(@RequestBody InquiryDto inquiryDto) {
        InquiryDto createdInquiry = inquiryService.createInquiry(inquiryDto);
        return ResponseEntity.ok(createdInquiry);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InquiryDto> updateInquiry(@PathVariable int id, @RequestBody InquiryDto inquiryDto) {
        InquiryDto updatedInquiry = inquiryService.updateInquiry(id, inquiryDto);
        if (updatedInquiry != null) {
            return ResponseEntity.ok(updatedInquiry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInquiry(@PathVariable int id) {
        inquiryService.deleteInquiry(id);
        return ResponseEntity.noContent().build();
    }
}

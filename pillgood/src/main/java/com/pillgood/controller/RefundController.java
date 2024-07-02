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

import com.pillgood.dto.RefundDto;
import com.pillgood.service.RefundService;

@RestController
@RequestMapping("/refunds")
public class RefundController {

    @Autowired
    private RefundService refundService;

    @GetMapping("/list")
    public List<RefundDto> getAllRefunds() {
        return refundService.getAllRefunds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefundDto> getRefundById(@PathVariable int id) {
        RefundDto refundDto = refundService.getRefundById(id);
        if (refundDto != null) {
            return ResponseEntity.ok(refundDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<RefundDto> createRefund(@RequestBody RefundDto refundDto) {
        RefundDto createdRefund = refundService.createRefund(refundDto);
        return ResponseEntity.ok(createdRefund);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RefundDto> updateRefund(@PathVariable int id, @RequestBody RefundDto refundDto) {
        RefundDto updatedRefund = refundService.updateRefund(id, refundDto);
        if (updatedRefund != null) {
            return ResponseEntity.ok(updatedRefund);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRefund(@PathVariable int id) {
        refundService.deleteRefund(id);
        return ResponseEntity.noContent().build();
    }
}

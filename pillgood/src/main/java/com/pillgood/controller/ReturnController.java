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

import com.pillgood.dto.ReturnDto;
import com.pillgood.service.ReturnService;
@RestController
@RequestMapping("/returns")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @GetMapping("/list")
    public List<ReturnDto> getAllReturns() {
        return returnService.getAllReturns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnDto> getReturnById(@PathVariable int id) {
        ReturnDto returnDto = returnService.getReturnById(id);
        if (returnDto != null) {
            return ResponseEntity.ok(returnDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ReturnDto> createReturn(@RequestBody ReturnDto returnDto) {
        ReturnDto createdReturn = returnService.createReturn(returnDto);
        return ResponseEntity.ok(createdReturn);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReturnDto> updateReturn(@PathVariable int id, @RequestBody ReturnDto returnDto) {
        ReturnDto updatedReturn = returnService.updateReturn(id, returnDto);
        if (updatedReturn != null) {
            return ResponseEntity.ok(updatedReturn);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReturn(@PathVariable int id) {
        returnService.deleteReturn(id);
        return ResponseEntity.noContent().build();
    }
}

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

import com.pillgood.dto.AnswerDto;
import com.pillgood.service.AnswerService;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/list")
    public List<AnswerDto> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerDto> getAnswerById(@PathVariable int id) {
        AnswerDto answerDto = answerService.getAnswerById(id);
        if (answerDto != null) {
            return ResponseEntity.ok(answerDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<AnswerDto> createAnswer(@RequestBody AnswerDto answerDto) {
        AnswerDto createdAnswer = answerService.createAnswer(answerDto);
        return ResponseEntity.ok(createdAnswer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AnswerDto> updateAnswer(@PathVariable int id, @RequestBody AnswerDto answerDto) {
        AnswerDto updatedAnswer = answerService.updateAnswer(id, answerDto);
        if (updatedAnswer != null) {
            return ResponseEntity.ok(updatedAnswer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable int id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}

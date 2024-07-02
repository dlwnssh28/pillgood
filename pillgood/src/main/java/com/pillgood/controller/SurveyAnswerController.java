package com.pillgood.controller;

import com.pillgood.dto.SurveyAnswerDto;
import com.pillgood.service.SurveyAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/surveyanswers")
public class SurveyAnswerController {

    @Autowired
    private SurveyAnswerService surveyAnswerService;

    @PostMapping("/create")
    public ResponseEntity<SurveyAnswerDto> createAnswer(@RequestBody SurveyAnswerDto answerDto) {
        return ResponseEntity.ok(surveyAnswerService.createAnswer(answerDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<SurveyAnswerDto>> getAllAnswers() {
        return ResponseEntity.ok(surveyAnswerService.getAllAnswers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyAnswerDto> getAnswerById(@PathVariable int id) {
        return ResponseEntity.ok(surveyAnswerService.getAnswerById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SurveyAnswerDto> updateAnswer(@PathVariable int id, @RequestBody SurveyAnswerDto answerDto) {
        return ResponseEntity.ok(surveyAnswerService.updateAnswer(id, answerDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable int id) {
        if (surveyAnswerService.deleteAnswer(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

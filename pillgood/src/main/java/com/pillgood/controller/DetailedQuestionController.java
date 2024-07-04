package com.pillgood.controller;

import com.pillgood.dto.DetailedQuestionDto;
import com.pillgood.service.DetailedQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detailed-questions/admin")
@RequiredArgsConstructor
public class DetailedQuestionController {

    private final DetailedQuestionService detailedQuestionService;

    @PostMapping("/create")
    public DetailedQuestionDto createDetailedQuestion(@RequestBody DetailedQuestionDto detailedQuestionDto) {
        return detailedQuestionService.createDetailedQuestion(detailedQuestionDto);
    }

    @GetMapping("/find/{id}")
    public Optional<DetailedQuestionDto> getDetailedQuestionById(@PathVariable int id) {
        return detailedQuestionService.getDetailedQuestionById(id);
    }

    @GetMapping("/list")
    public List<DetailedQuestionDto> getAllDetailedQuestions() {
        return detailedQuestionService.getAllDetailedQuestions();
    }

    @PutMapping("/update/{id}")
    public Optional<DetailedQuestionDto> updateDetailedQuestion(@PathVariable int id, @RequestBody DetailedQuestionDto detailedQuestionDto) {
        return detailedQuestionService.updateDetailedQuestion(id, detailedQuestionDto);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteDetailedQuestion(@PathVariable int id) {
        return detailedQuestionService.deleteDetailedQuestion(id);
    }
}

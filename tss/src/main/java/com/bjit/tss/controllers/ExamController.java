package com.bjit.tss.controllers;

import com.bjit.tss.model.ExamModel;
import com.bjit.tss.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public ResponseEntity<Object> createExam(@RequestBody ExamModel examModel) {
        return examService.createExam(examModel);
    }

    @PutMapping("/{examCode}")
    public ResponseEntity<Object> updateExamByExamCode(@PathVariable Long examCode, @RequestBody ExamModel examModel) {
        return examService.updateExamByExamCode(examCode, examModel);
    }

    @DeleteMapping("/{examCode}")
    public ResponseEntity<Object> deleteExamByExamCode(@PathVariable Long examCode) {
        return examService.deleteExamByExamCode(examCode);
    }

    @GetMapping
    public ResponseEntity<Object> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{examId}")
    public ResponseEntity<Object> getExamById(@PathVariable Long examId) {
        return examService.getExamById(examId);
    }

    @GetMapping("/code/{examCode}")
    public ResponseEntity<Object> getExamByExamCode(@PathVariable Long examCode) {
        return examService.getExamByExamCode(examCode);
    }
}

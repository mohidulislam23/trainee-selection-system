package com.bjit.tss.controllers;

import com.bjit.tss.model.MarkModel;
import com.bjit.tss.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mark")
@RequiredArgsConstructor
public class MarkController {
    private final MarkService markService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveMark(@RequestBody MarkModel markModel) {
        return markService.saveMark(markModel);
    }

    @PutMapping("/update/{markId}")
    public ResponseEntity<Object> updateMark(@PathVariable Long markId, @RequestBody MarkModel markModel) {
        return markService.updateMark(markId, markModel);
    }

    @GetMapping("/{markId}")
    public ResponseEntity<Object> getMarkById(@PathVariable Long markId) {
        return markService.getMarkById(markId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllMarks() {
        return markService.getAllMarks();
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<Object> getMarkByApplicantId(@PathVariable Long applicantId) {
        return markService.getMarkByApplicantId(applicantId);
    }

    @GetMapping("/circular/{circular}")
    public ResponseEntity<Object> getMarkByCircular(@PathVariable String circular) {
        return markService.getMarkByCircular(circular);
    }

    @DeleteMapping("/delete/{markId}")
    public ResponseEntity<Object> deleteMark(@PathVariable Long markId) {
        return markService.deleteMark(markId);
    }
}

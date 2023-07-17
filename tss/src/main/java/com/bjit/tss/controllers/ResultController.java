package com.bjit.tss.controllers;

import com.bjit.tss.model.ResultModel;
import com.bjit.tss.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveResult(@RequestBody ResultModel resultModel) {
        return resultService.saveResult(resultModel);
    }

    @PutMapping("/update/{resultId}")
    public ResponseEntity<Object> updateResult(@PathVariable Long resultId, @RequestBody ResultModel resultModel) {
        return resultService.updateResult(resultId, resultModel);
    }

    @GetMapping("/{resultId}")
    public ResponseEntity<Object> getResultById(@PathVariable Long resultId) {
        return resultService.getResultById(resultId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<Object> getResultsByApplicantId(@PathVariable Long applicantId) {
        return resultService.getResultsByApplicantId(applicantId);
    }

    @GetMapping("/circular/{circularTitle}")
    public ResponseEntity<Object> getResultsByCircularTitle(@PathVariable String circularTitle) {
        return resultService.getResultsByCircularTitle(circularTitle);
    }

    @DeleteMapping("/{resultId}")
    public ResponseEntity<Object> deleteResult(@PathVariable Long resultId) {
        return resultService.deleteResult(resultId);
    }
}

package com.bjit.tss.controllers;

import com.bjit.tss.model.ResultModel;
import com.bjit.tss.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;
    private boolean isResultGenerated = false;

    @PostMapping("/generate")
    public ResponseEntity<Object> saveResult(@RequestBody ResultModel resultModel) {
        if (isResultGenerated) {
            // Result has already been generated, return an error response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Result has already been generated. Cannot generate again.");
        }

        // Process the resultModel and save the result
        ResponseEntity<Object> response = resultService.saveResult(resultModel);

        // Set the flag to indicate that the result has been generated
        isResultGenerated = true;

        return response;
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


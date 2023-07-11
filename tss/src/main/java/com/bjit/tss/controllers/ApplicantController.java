package com.bjit.tss.controllers;

import com.bjit.tss.model.ApplicantModel;
import com.bjit.tss.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicant")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping
    public ResponseEntity<Object> createApplicant(@RequestBody ApplicantModel applicantModel) {
        return applicantService.createApplicant(applicantModel);
    }

    @PutMapping("/{applicantId}")
    public ResponseEntity<Object> updateApplicant(@PathVariable Long applicantId, @RequestBody ApplicantModel applicantModel) {
        return applicantService.updateApplicant(applicantId, applicantModel);
    }

    @DeleteMapping("/{applicantId}")
    public ResponseEntity<Object> deleteApplicant(@PathVariable Long applicantId) {
        return applicantService.deleteApplicant(applicantId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllApplicants() {
        return applicantService.getAllApplicants();
    }

    @GetMapping("/{applicantId}")
    public ResponseEntity<Object> getApplicantById(@PathVariable Long applicantId) {
        return applicantService.getApplicantById(applicantId);
    }
}

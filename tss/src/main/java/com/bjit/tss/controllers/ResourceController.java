package com.bjit.tss.controllers;

import com.bjit.tss.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/resource/applicant")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;


    @PostMapping("/{applicantId}")
    public ResponseEntity<Object> saveResourceByApplicantId(@PathVariable Long applicantId, @RequestBody MultipartFile photo, @RequestBody MultipartFile cv) {
        return resourceService.saveResourceByApplicantId(applicantId, photo, cv);
    }

    @GetMapping("/{applicantId}")
    public ResponseEntity<Object> getResourceByApplicantId(@PathVariable Long applicantId) {
        return resourceService.getResourceByApplicantId(applicantId);
    }

    @DeleteMapping("/{applicantId}")
    public ResponseEntity<Object> deleteResourceByApplicantId(@PathVariable Long applicantId) {
        return resourceService.deleteResourceByApplicantId(applicantId);
    }
}

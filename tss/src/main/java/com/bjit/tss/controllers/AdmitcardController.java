package com.bjit.tss.controllers;

import com.bjit.tss.model.AdmitcardModel;
import com.bjit.tss.service.AdmitcardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admitcard")
@RequiredArgsConstructor
public class AdmitcardController {

    private final AdmitcardService admitcardService;

    @PostMapping
    public ResponseEntity<Object> createAdmitcard(@RequestBody AdmitcardModel admitcardModel) {
        return admitcardService.createAdmitcard(admitcardModel);
    }

    @PutMapping("/{admitcardId}")
    public ResponseEntity<Object> updateAdmitcard(@PathVariable Long admitcardId, @RequestBody AdmitcardModel admitcardModel) {
        return admitcardService.updateAdmitcard(admitcardId, admitcardModel);
    }

    @DeleteMapping("/{admitcardId}")
    public ResponseEntity<Object> deleteAdmitcard(@PathVariable Long admitcardId) {
        return admitcardService.deleteAdmitcard(admitcardId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllAdmitcards() {
        return admitcardService.getAllAdmitcards();
    }

    @GetMapping("/{applicantId}")
    public ResponseEntity<Object> getAdmitcardById(@PathVariable Long applicantId) {
        return admitcardService.getAdmitcardById(applicantId);
    }

    @GetMapping("/{applicantId}/pdf")
    public ResponseEntity<Object> generateAdmitcardPdf(@PathVariable Long applicantId) {
        return admitcardService.generateAdmitcardPdf(applicantId);
    }
}


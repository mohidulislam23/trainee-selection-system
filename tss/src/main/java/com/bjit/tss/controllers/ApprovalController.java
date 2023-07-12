package com.bjit.tss.controllers;

import com.bjit.tss.model.ApprovalModel;
import com.bjit.tss.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/approval")
@RequiredArgsConstructor
public class ApprovalController {
    private final ApprovalService approvalService;

    @PostMapping
    public ResponseEntity<Object> createApproval(@RequestBody ApprovalModel approvalModel) {
        return approvalService.createApproval(approvalModel);
    }

    @PutMapping("/{approvalId}")
    public ResponseEntity<Object> updateApproval(@PathVariable Long approvalId, @RequestBody ApprovalModel approvalModel) {
        return approvalService.updateApproval(approvalId, approvalModel);
    }

    @DeleteMapping("/{approvalId}")
    public ResponseEntity<Object> deleteApproval(@PathVariable Long approvalId, @RequestBody ApprovalModel approvalModel) {
        return approvalService.deleteApproval(approvalId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllApprovals() {
        return approvalService.getAllApprovals();
    }

    @GetMapping("/{approvalId}")
    public ResponseEntity<Object> getApprovalsById(@PathVariable Long approvalId) {
        return approvalService.getApprovalById(approvalId);
    }
}

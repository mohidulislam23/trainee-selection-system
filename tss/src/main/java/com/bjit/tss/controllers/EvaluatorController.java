package com.bjit.tss.controllers;

import com.bjit.tss.model.EvaluatorModel;
import com.bjit.tss.service.EvaluatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluator")
@RequiredArgsConstructor
public class EvaluatorController {

    private final EvaluatorService evaluatorService;

    @PostMapping
    public ResponseEntity<Object> createEvaluator(@RequestBody EvaluatorModel evaluatorModel) {
        return evaluatorService.createEvaluator(evaluatorModel);
    }

    @PutMapping("/{evaluatorId}")
    public ResponseEntity<Object> updateEvaluator(@PathVariable Long evaluatorId, @RequestBody EvaluatorModel evaluatorModel) {
        return evaluatorService.updateEvaluator(evaluatorId, evaluatorModel);
    }

    @DeleteMapping("/{evaluatorId}")
    public ResponseEntity<Object> deleteEvaluator(@PathVariable Long evaluatorId) {
        return evaluatorService.deleteEvaluator(evaluatorId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllEvaluators() {
        return evaluatorService.getAllEvaluators();
    }

    @GetMapping("/{evaluatorId}")
    public ResponseEntity<Object> getEvaluatorById(@PathVariable Long evaluatorId) {
        return evaluatorService.getEvaluatorById(evaluatorId);
    }
}


package com.bjit.tss.controllers;

import com.bjit.tss.model.CircularModel;
import com.bjit.tss.service.CircularService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/circular")
@RequiredArgsConstructor
public class CircularController {
    private final CircularService circularService;

    @PostMapping
    public ResponseEntity<Object> createCircular(@RequestBody CircularModel circularModel) {
        return circularService.createCircular(circularModel);
    }

    @PutMapping("/{circularId}")
    public ResponseEntity<Object> updateCircular(@PathVariable Long circularId, @RequestBody CircularModel circularModel) {
        return circularService.updateCircular(circularId, circularModel);
    }

    @DeleteMapping("/{circularId}")
    public ResponseEntity<Object> deleteCircular(@PathVariable Long circularId) {
        return circularService.deleteCircular(circularId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllCirculars() {
        return circularService.getAllCirculars();
    }

    @GetMapping("/{circularId}")
    public ResponseEntity<Object> getDetailById(@PathVariable Long circularId) {
        return circularService.getDetailById(circularId);
    }
}

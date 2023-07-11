package com.bjit.tss.controllers;

import com.bjit.tss.model.MailModel;
import com.bjit.tss.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping
    public ResponseEntity<Object> createMail(@RequestBody MailModel mailModel) {
        return mailService.createMail(mailModel);
    }

    @PutMapping("/{mailId}")
    public ResponseEntity<Object> updateMail(@PathVariable Long mailId, @RequestBody MailModel mailModel) {
        return mailService.updateMail(mailId, mailModel);
    }

    @DeleteMapping("/{mailId}")
    public ResponseEntity<Object> deleteMail(@PathVariable Long mailId) {
        return mailService.deleteMail(mailId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllMails() {
        return mailService.getAllMails();
    }

    @GetMapping("/{mailId}")
    public ResponseEntity<Object> getMailById(@PathVariable Long mailId) {
        return mailService.getMailById(mailId);
    }
}


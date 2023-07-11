package com.bjit.tss.service.Implementation;

import com.bjit.tss.entity.MailEntity;
import com.bjit.tss.model.MailModel;
import com.bjit.tss.repository.MailRepository;
import com.bjit.tss.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MailServiceImplementation implements MailService {

    private final MailRepository mailRepository;

    public MailServiceImplementation(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    @Override
    public ResponseEntity<Object> createMail(MailModel mailModel) {
        MailEntity mailEntity = MailEntity.builder()
                .sender(mailModel.getSender())
                .subject(mailModel.getSubject())
                .body(mailModel.getBody())
                .timestamp(new Date())
                .build();

        MailEntity savedMail = mailRepository.save(mailEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMail);
    }

    @Override
    public ResponseEntity<Object> updateMail(Long mailId, MailModel mailModel) {
        Optional<MailEntity> optionalMail = mailRepository.findById(mailId);
        if (optionalMail.isPresent()) {
            MailEntity existingMail = optionalMail.get();
            existingMail.setSender(mailModel.getSender());
            existingMail.setSubject(mailModel.getSubject());
            existingMail.setBody(mailModel.getBody());
            existingMail.setTimestamp(new Date());

            mailRepository.save(existingMail);
            return ResponseEntity.ok(existingMail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> deleteMail(Long mailId) {
        Optional<MailEntity> optionalMail = mailRepository.findById(mailId);
        if (optionalMail.isPresent()) {
            MailEntity existingMail = optionalMail.get();
            mailRepository.delete(existingMail);
            return ResponseEntity.ok(existingMail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> getAllMails() {
        List<MailEntity> mails = mailRepository.findAll();
        return ResponseEntity.ok(mails);
    }

    @Override
    public ResponseEntity<Object> getMailById(Long mailId) {
        Optional<MailEntity> optionalMail = mailRepository.findById(mailId);
        if (optionalMail.isPresent()) {
            MailEntity mail = optionalMail.get();
            return ResponseEntity.ok(mail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

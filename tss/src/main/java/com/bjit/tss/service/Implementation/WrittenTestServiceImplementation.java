package com.bjit.tss.service.Implementation;

import com.bjit.tss.entity.AdmitcardEntity;
import com.bjit.tss.entity.WrittenTestEntity;
import com.bjit.tss.model.WrittenTestModel;
import com.bjit.tss.repository.AdmitcardRepository;
import com.bjit.tss.repository.WrittenTestRepository;
import com.bjit.tss.service.WrittenTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WrittenTestServiceImplementation implements WrittenTestService {

    private final WrittenTestRepository writtenTestRepository;
    private final AdmitcardRepository admitCardRepository;

    @Override
    public ResponseEntity<Object> getWrittenTestById(Long writtenTestId) {
        WrittenTestEntity writtenTest = writtenTestRepository.findById(writtenTestId).orElse(null);
        if (writtenTest == null) {
            return new ResponseEntity<>("Written test not found", HttpStatus.NOT_FOUND);
        }
        WrittenTestModel writtenTestModel = convertToModel(writtenTest);
        return new ResponseEntity<>(writtenTestModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllWrittenTest() {
        List<WrittenTestEntity> writtenTests = writtenTestRepository.findAll();
        List<WrittenTestModel> writtenTestModels = writtenTests.stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
        return new ResponseEntity<>(writtenTestModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> createWrittenTest(WrittenTestModel writtenTestModel) {
        WrittenTestEntity writtenTest = new WrittenTestEntity();
        writtenTest.setHiddenCode(writtenTestModel.getHiddenCode());
        writtenTest.setApplicantId(writtenTestModel.getApplicantId());
        writtenTest.setMark(writtenTestModel.getMark());

        WrittenTestEntity savedWrittenTest = writtenTestRepository.save(writtenTest);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedWrittenTest);
    }


    @Override
    public ResponseEntity<Object> updateWrittenTest(Long writtenTestId, WrittenTestModel writtenTestModel) {
        WrittenTestEntity existingWrittenTest = writtenTestRepository.findById(writtenTestId).orElse(null);
        if (existingWrittenTest == null) {
            return new ResponseEntity<>("Written test not found", HttpStatus.NOT_FOUND);
        }
        existingWrittenTest.setHiddenCode(writtenTestModel.getHiddenCode());
        existingWrittenTest.setApplicantId(writtenTestModel.getApplicantId());
        existingWrittenTest.setMark(writtenTestModel.getMark());
        WrittenTestEntity updatedWrittenTest = writtenTestRepository.save(existingWrittenTest);
        WrittenTestModel updatedWrittenTestModel = convertToModel(updatedWrittenTest);
        return new ResponseEntity<>(updatedWrittenTestModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteWrittenTest(Long writtenTestId) {
        WrittenTestEntity writtenTest = writtenTestRepository.findById(writtenTestId).orElse(null);
        if (writtenTest == null) {
            return new ResponseEntity<>("Written test not found", HttpStatus.NOT_FOUND);
        }
        writtenTestRepository.delete(writtenTest);
        return new ResponseEntity<>("Written test deleted successfully", HttpStatus.OK);
    }

    private WrittenTestModel convertToModel(WrittenTestEntity writtenTestEntity) {
        WrittenTestModel writtenTestModel = new WrittenTestModel();
        writtenTestModel.setWrittenId(writtenTestEntity.getWrittenId());
        writtenTestModel.setHiddenCode(writtenTestEntity.getHiddenCode());
        writtenTestModel.setApplicantId(writtenTestEntity.getApplicantId());
        writtenTestModel.setMark(writtenTestEntity.getMark());
        return writtenTestModel;
    }

    private WrittenTestEntity convertToEntity(WrittenTestModel writtenTestModel) {
        WrittenTestEntity writtenTestEntity = new WrittenTestEntity();
        writtenTestEntity.setWrittenId(writtenTestModel.getWrittenId());
        writtenTestEntity.setHiddenCode(writtenTestModel.getHiddenCode());
        writtenTestEntity.setApplicantId(writtenTestModel.getApplicantId());
        writtenTestEntity.setMark(writtenTestModel.getMark());
        return writtenTestEntity;
    }
    @Override
    public ResponseEntity<Object> autoCreateWrittenTest() {
        List<AdmitcardEntity> admitCards = admitCardRepository.findAll();
        if (admitCards.isEmpty()) {
            return new ResponseEntity<>("No admit cards found", HttpStatus.NOT_FOUND);
        }

        List<WrittenTestModel> createdWrittenTests = new ArrayList<>();

        for (AdmitcardEntity admitCard : admitCards) {
            Long applicantId = admitCard.getCandidateId().getApplicant().getApplicantId();

            // Generate hiddenCode
            //AtomicLong counter = new AtomicLong(10000);
            Long hiddenCode = generateSerialNumberSerially();

            // Create WrittenTestEntity
            WrittenTestEntity writtenTest = new WrittenTestEntity();
            writtenTest.setApplicantId(applicantId);
            writtenTest.setHiddenCode(hiddenCode);
            writtenTest.setMark(0.0);
            // Set other properties of WrittenTestEntity as required

            // Save the WrittenTestEntity
            WrittenTestEntity savedWrittenTest = writtenTestRepository.save(writtenTest);
            WrittenTestModel savedWrittenTestModel = convertToModel(savedWrittenTest);
            createdWrittenTests.add(savedWrittenTestModel);
        }

        return new ResponseEntity<>(createdWrittenTests, HttpStatus.CREATED);
    }


    private AtomicLong counter = new AtomicLong(100);

    private Long generateSerialNumberSerially() {
        return counter.incrementAndGet();
    }

    @Override
    public ResponseEntity<Object> updateWrittenTest(Long hiddenCode, Double mark) {
        // Check if hiddenCode is valid
        WrittenTestEntity existingWrittenTest = writtenTestRepository.findByHiddenCode(hiddenCode);
        if (existingWrittenTest == null) {
            return new ResponseEntity<>("Invalid hiddenCode", HttpStatus.NOT_FOUND);
        }

        // Update the mark of the existingWrittenTest
        existingWrittenTest.setMark(mark);

        // Save the updated WrittenTestEntity
        WrittenTestEntity updatedWrittenTest = writtenTestRepository.save(existingWrittenTest);
        WrittenTestModel updatedWrittenTestModel = convertToModel(updatedWrittenTest);
        return new ResponseEntity<>(updatedWrittenTestModel, HttpStatus.OK);
    }


}
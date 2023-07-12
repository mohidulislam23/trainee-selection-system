package com.bjit.tss.service.Implementation;

import com.bjit.tss.entity.AdmitcardEntity;
import com.bjit.tss.entity.ApprovalEntity;
import com.bjit.tss.model.AdmitcardModel;
import com.bjit.tss.repository.AdmitcardRepository;
import com.bjit.tss.repository.ApprovalRepository;
import com.bjit.tss.service.AdmitcardService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdmitcardServiceImplementation implements AdmitcardService {

    private final AdmitcardRepository admitcardRepository;
    private final ApprovalRepository approvalRepository;

    public AdmitcardServiceImplementation(AdmitcardRepository admitcardRepository, ApprovalRepository approvalRepository) {
        this.admitcardRepository = admitcardRepository;
        this.approvalRepository = approvalRepository;
    }

    @Override
    public ResponseEntity<Object> createAdmitcard(AdmitcardModel admitcardModel) {
        AdmitcardEntity admitcardEntity = new AdmitcardEntity();

        // Retrieve the ApprovalEntity using candidateId from admitcardModel
        Optional<ApprovalEntity> optionalApproval = approvalRepository.findById(admitcardModel.getCandidateId().getApprovalId());
        if (optionalApproval.isPresent()) {
            ApprovalEntity approvalEntity = optionalApproval.get();
            admitcardEntity.setCandidateId(approvalEntity);
        } else {
            return ResponseEntity.notFound().build();
        }

        // Generate serial number (assuming it's system-generated)
        Long generatedSerialNumber = generateSerialNumber();
        admitcardEntity.setSerialNumber(generatedSerialNumber);

        AdmitcardEntity savedAdmitcard = admitcardRepository.save(admitcardEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmitcard);
    }

    @Override
    public ResponseEntity<Object> updateAdmitcard(Long admitcardId, AdmitcardModel admitcardModel) {
        Optional<AdmitcardEntity> optionalAdmitcard = admitcardRepository.findById(admitcardId);
        if (optionalAdmitcard.isPresent()) {
            AdmitcardEntity existingAdmitcard = optionalAdmitcard.get();

            // Retrieve the ApprovalEntity using candidateId from admitcardModel
            Optional<ApprovalEntity> optionalApproval = approvalRepository.findById(admitcardModel.getCandidateId().getApprovalId());
            if (optionalApproval.isPresent()) {
                ApprovalEntity approvalEntity = optionalApproval.get();
                existingAdmitcard.setCandidateId(approvalEntity);
            } else {
                return ResponseEntity.notFound().build();
            }

            existingAdmitcard.setSerialNumber(existingAdmitcard.getSerialNumber()); // Assuming serialNumber is not updated in the update operation

            admitcardRepository.save(existingAdmitcard);
            return ResponseEntity.ok(existingAdmitcard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> deleteAdmitcard(Long admitcardId) {
        Optional<AdmitcardEntity> optionalAdmitcard = admitcardRepository.findById(admitcardId);
        if (optionalAdmitcard.isPresent()) {
            AdmitcardEntity existingAdmitcard = optionalAdmitcard.get();
            admitcardRepository.delete(existingAdmitcard);
            return ResponseEntity.ok(existingAdmitcard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> getAllAdmitcards() {
        List<AdmitcardEntity> admitcards = admitcardRepository.findAll();
        return ResponseEntity.ok(admitcards);
    }

    @Override
    public ResponseEntity<Object> getAdmitcardById(Long admitcardId) {
        Optional<AdmitcardEntity> optionalAdmitcard = admitcardRepository.findById(admitcardId);
        if (optionalAdmitcard.isPresent()) {
            AdmitcardEntity admitcard = optionalAdmitcard.get();
            return ResponseEntity.ok(admitcard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> generateAdmitcardPdf(Long admitcardId) {
        Optional<AdmitcardEntity> optionalAdmitcard = admitcardRepository.findById(admitcardId);
        if (optionalAdmitcard.isPresent()) {
            AdmitcardEntity admitcard = optionalAdmitcard.get();
            // Logic to generate the Admit Card in PDF format
            // You can use a PDF library such as iText, Apache PDFBox, or PDFKit to generate the PDF

            // Implement the logic to generate the Admit Card PDF and return it as a response

            // Example: Assuming you generate the PDF and store it in a byte array called 'pdfBytes'
            byte[] pdfBytes = generateAdmitcardPdfBytes(admitcard);

            // Return the PDF as a response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "admitcard.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Long generateSerialNumber() {
        // Logic to generate a unique serial number
        // You can implement your own custom logic here to generate the serial number
        // For simplicity, let's assume it's a UUID-based serial number
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    private byte[] generateAdmitcardPdfBytes(AdmitcardEntity admitcard) {
        // Logic to generate the Admit Card PDF using a PDF library
        // Customize the implementation based on your requirements and chosen PDF library

        // Example: Generating a dummy PDF with the admit card details
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputStream));
             Document document = new Document(pdfDocument)) {

            // Add content to the PDF document
            document.add(new Paragraph("Admit Card"));
            document.add(new Paragraph("Candidate Name: " + admitcard.getCandidateId().getApplicant().getFirstName() + " " + admitcard.getCandidateId().getApplicant().getLastName()));
            document.add(new Paragraph("Serial Number: " + admitcard.getSerialNumber()));

            // ...

            // Save and close the PDF document
            pdfDocument.close();

            // Retrieve the byte array of the generated PDF
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if PDF generation fails
    }
}

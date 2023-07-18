package com.bjit.tss.service.Implementation;

import com.bjit.tss.entity.AdmitcardEntity;
import com.bjit.tss.entity.ApprovalEntity;
import com.bjit.tss.model.AdmitcardModel;
import com.bjit.tss.repository.AdmitcardRepository;
import com.bjit.tss.repository.ApprovalRepository;
import com.bjit.tss.service.AdmitcardService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

//for QR Code
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.QRCode;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.imageio.ImageIO;


@Service
@RequiredArgsConstructor
public class AdmitcardServiceImplementation implements AdmitcardService {

    private final AdmitcardRepository admitcardRepository;
    private final ApprovalRepository approvalRepository;


    @Override
    public ResponseEntity<Object> createAdmitcard(AdmitcardModel admitcardModel) {
        // Retrieve all approved approvals
        List<ApprovalEntity> approvedApprovals = approvalRepository.findByIsApprovedTrue();

        if (approvedApprovals.isEmpty()) {
            return ResponseEntity.badRequest().body("No approved approvals found.");
        }

        List<AdmitcardEntity> admitcards = new ArrayList<>();

        for (ApprovalEntity approvalEntity : approvedApprovals) {
            AdmitcardEntity admitcardEntity = new AdmitcardEntity();
            admitcardEntity.setCandidateId(approvalEntity);

            // Generate serial number (assuming it's system-generated)
            // Long generatedSerialNumber = generateSerialNumber(); // UUID based
            Long generatedSerialNumber = generateSerialNumberSerially(); // Sequence based
            admitcardEntity.setSerialNumber(generatedSerialNumber);

            admitcards.add(admitcardEntity);
        }

        List<AdmitcardEntity> savedAdmitcards = admitcardRepository.saveAll(admitcards);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmitcards);
    }



//    @Override
//    public ResponseEntity<Object> createAdmitcard(AdmitcardModel admitcardModel) {
//        // Retrieve the ApprovalEntity using candidateId from admitcardModel
//        Optional<ApprovalEntity> optionalApproval = approvalRepository.findById(admitcardModel.getCandidateId().getApprovalId());
//        if (optionalApproval.isPresent()) {
//            ApprovalEntity approvalEntity = optionalApproval.get();
//
//            // Check if the approval is approved
//            if (approvalEntity.isApproved()) {
//                AdmitcardEntity admitcardEntity = new AdmitcardEntity();
//                admitcardEntity.setCandidateId(approvalEntity);
//
//                // Generate serial number (assuming it's system-generated)
//                //Long generatedSerialNumber = generateSerialNumber(); //UUID based
//                Long generatedSerialNumber = generateSerialNumberSerially();  //Sequence based
//                admitcardEntity.setSerialNumber(generatedSerialNumber);
//
//                AdmitcardEntity savedAdmitcard = admitcardRepository.save(admitcardEntity);
//                return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmitcard);
//            } else {
//                return ResponseEntity.badRequest().body("Approval is not yet approved.");
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


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
            // PDF library such as iText, Apache PDFBox, or PDFKit to generate the PDF

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

    // UUID-based serial number generation
    private Long generateSerialNumber() {
        // For simplicity, we assume it's a UUID-based serial number
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    //////////////////////////////////////////////////////////////////////////////////
    // Logic to generate a unique serial number
    // I have implement my own custom logic here to generate the serial number serially
    // If you want data serially, use generateSerialNumberSerially() in createAdmit method
    private AtomicLong counter = new AtomicLong(10000);
    private Long generateSerialNumberSerially() {
        return counter.incrementAndGet();
    }
    /////////////////////////////////////////////////////////////////////////////////


    private byte[] generateAdmitcardPdfBytes(AdmitcardEntity admitcard) {
        // Generating a dummy PDF with the admit card details and QR code
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputStream));
             Document document = new Document(pdfDocument)) {

            // Retrieve the Exam name from circularId
            String examName = admitcard.getCandidateId().getCircular().getTitle();

            // Retrieve the candidate name
            String candidateName = admitcard.getCandidateId().getApplicant().getFirstName() + " " + admitcard.getCandidateId().getApplicant().getLastName();

            // Retrieve the serial number
            Long serialNumber = admitcard.getSerialNumber();

            // Add content to the PDF document
            document.add(new Paragraph("Admit Card"));
            document.add(new Paragraph("Exam Name: " + examName));
            document.add(new Paragraph("Candidate Name: " + candidateName));
            document.add(new Paragraph("Serial Number: " + serialNumber));
            document.add(new Paragraph("Details QR Code: \n"));


            // Generate QR code containing information about the applicant
            String qrCodeContent = "Applicant:\n" +
                    "Name: " + admitcard.getCandidateId().getApplicant().getFirstName() + " " + admitcard.getCandidateId().getApplicant().getLastName() + "\n" +
                    "Gender: " + admitcard.getCandidateId().getApplicant().getGender() + "\n" +
                    "Date of Birth: " + admitcard.getCandidateId().getApplicant().getDob() + "\n" +
                    "Email: " + admitcard.getCandidateId().getApplicant().getEmail() + "\n" +
                    "Contact Number: " + admitcard.getCandidateId().getApplicant().getContactNumber() + "\n" +
                    "Degree Name: " + admitcard.getCandidateId().getApplicant().getDegreeName() + "\n" +
                    "Educational Institute: " + admitcard.getCandidateId().getApplicant().getEducationalInstitute() + "\n" +
                    "CGPA: " + admitcard.getCandidateId().getApplicant().getCgpa() + "\n" +
                    "Passing Year: " + admitcard.getCandidateId().getApplicant().getPassingYear() + "\n" +
                    "Present Address: " + admitcard.getCandidateId().getApplicant().getPresentAddress();


            byte[] qrCodeBytes = generateQRCode(qrCodeContent);
            if (qrCodeBytes != null) {
                // Embed the QR code image in the PDF document
                Image qrCodeImage = new Image(ImageDataFactory.create(qrCodeBytes));
                document.add(qrCodeImage);
            }

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

    private byte[] generateQRCode(String content) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

            // Generate the QR code matrix
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);

            // Convert the matrix to a BufferedImage
            BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // Convert the image to a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, "PNG", outputStream);
            return outputStream.toByteArray();
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if QR code generation fails
    }

}

package com.bjit.tss.repository;

import com.bjit.tss.entity.AdmitcardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdmitcardRepository extends JpaRepository<AdmitcardEntity, Long> {
    //Optional<AdmitcardEntity> findByCandidateId_ApprovalId_ApplicantId(Long );
    //@Query("SELECT a FROM AdmitcardEntity a WHERE a.candidateId.approvalId.applicant.applicantId = :applicantId")
    //Optional<AdmitcardEntity> findByCandidateId_ApprovalId_ApplicantId(@Param("applicantId") Long applicantId);

    //Optional<AdmitcardEntity> findByCandidateId_ApprovalId_ApplicantId(Long applicantId);

    //Optional<AdmitcardEntity> findByCandidateId_ApprovalId_Applicant_ApplicantId(Long applicantId);

    //List<AdmitcardEntity> findByCandidateId_ApprovalId_ApplicantId(Long );
    //Optional<AdmitcardEntity> findByCandidateId_ApprovalId_ApplicantId(Long applicantId);
}

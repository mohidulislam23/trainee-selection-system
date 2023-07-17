package com.bjit.tss.repository;

import com.bjit.tss.entity.AdmitcardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdmitcardRepository extends JpaRepository<AdmitcardEntity, Long> {
    //List<AdmitcardEntity> findByCandidateId_ApprovalId_ApplicantId(Long );
    //Optional<AdmitcardEntity> findByCandidateId_ApprovalId_ApplicantId(Long applicantId);
}

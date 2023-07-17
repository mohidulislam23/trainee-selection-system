package com.bjit.tss.repository;

import com.bjit.tss.entity.ApprovalEntity;
import com.bjit.tss.entity.CircularEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity, Long> {
    List<ApprovalEntity> findByApplicant_ApplicantIdAndCircularNot(Long applicantId, CircularEntity circular);

}

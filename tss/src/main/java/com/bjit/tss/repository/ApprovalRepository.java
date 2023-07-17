package com.bjit.tss.repository;

import com.bjit.tss.entity.ApplicantEntity;
import com.bjit.tss.entity.ApprovalEntity;
import com.bjit.tss.entity.CircularEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity, Long> {
    List<ApprovalEntity> findByApplicant_ApplicantIdAndCircularNot(Long applicantId, CircularEntity circular);
    //List<ApprovalEntity> findByApplicant_ApplicantIdAndCircularNot(Long applicantId, CircularEntity circular);
    //List<ApprovalEntity> findByApplicant_ApplicantIdAndCircularNot(Long applicantId, CircularEntity circular);
    //List<ApprovalEntity> findByApplicant_ApplicantIdAndCircularNot(ApplicantEntity applicant, CircularEntity circular);

    //Optional<ApprovalEntity> findByApplicant_ApplicantIdAndCircularNot(ApplicantEntity applicant, CircularEntity circular);
    //List<ApprovalEntity> findByApplicant_ApplicantIdAndCircularNot(ApplicantEntity applicant, CircularEntity circular);
    //Optional<ApprovalEntity> findByApplicant_ApplicantIdAndCircularNot(Long applicantId, CircularEntity circular);

//    @Query("SELECT a FROM ApprovalEntity a WHERE a.applicant = :applicant AND a.circular <> :circular")
//    Optional<ApprovalEntity> findByApplicant_ApplicantIdAndCircularNot(@Param("applicant_id") ApplicantEntity applicant, @Param("circular_id") CircularEntity circular);
}

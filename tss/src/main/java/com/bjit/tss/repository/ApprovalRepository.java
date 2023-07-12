package com.bjit.tss.repository;

import com.bjit.tss.entity.ApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity, Long> {
}

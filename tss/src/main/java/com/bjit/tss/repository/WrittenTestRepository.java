package com.bjit.tss.repository;

import com.bjit.tss.entity.WrittenTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WrittenTestRepository extends JpaRepository<WrittenTestEntity, Long> {
    WrittenTestEntity findByHiddenCode(Long hiddenCode);
}
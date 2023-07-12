package com.bjit.tss.repository;

import com.bjit.tss.entity.AdmitcardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmitcardRepository extends JpaRepository<AdmitcardEntity, Long> {
}

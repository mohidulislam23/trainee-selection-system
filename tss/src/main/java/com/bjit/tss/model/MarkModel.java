package com.bjit.tss.model;

import com.bjit.tss.entity.ExamEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarkModel {
    private Long markId;
    private ExamEntity exam;
    private String circular;
    private Long applicantId;
    private Double mark;
}

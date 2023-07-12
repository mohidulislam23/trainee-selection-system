package com.bjit.tss.model;

import com.bjit.tss.entity.ApprovalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmitcardModel {
    private Long admitcardId;
    private ApprovalEntity candidateId;
    private Long serialNumber;
}

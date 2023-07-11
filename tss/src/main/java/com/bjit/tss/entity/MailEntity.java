package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="mails")
public class MailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailId;
    private String sender;
    // apatoto reciepient dorkar nai
    //private String reciepient; // eta applicant email er sasthe one to many connection hobe <--implement it future
    private String subject;
    private String body;
    private Date timestamp;
}
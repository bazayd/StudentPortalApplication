package com.cs157.StudentPortal.model;

import jdk.jfr.Unsigned;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MessageID;

    private int StudentID;

    private String MessageTitle;

    private LocalDate MessageDate;

    private String MessageBody;

    public Messages(){}

    public Messages(int MessageID, int StudentID, String MessageTitle, LocalDate MessageDate, String MessageBody) {
        this.MessageID = MessageID;
        this.StudentID = StudentID;
        this.MessageTitle = MessageTitle;
        this.MessageDate = MessageDate;
        this.MessageBody = MessageBody;
    }

    public int getMessageID() {
        return MessageID;
    }

    public void setMessageID(int messageID) {
        MessageID = messageID;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public String getMessageTitle() {
        return MessageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        MessageTitle = messageTitle;
    }

    public LocalDate getMessageDate() {
        return MessageDate;
    }

    public void setMessageDate(LocalDate messageDate) {
        MessageDate = messageDate;
    }

    public String getMessageBody() {
        return MessageBody;
    }

    public void setMessageBody(String messageBody) {
        MessageBody = messageBody;
    }
}

package com.cs157.StudentPortal.model;

import jdk.jfr.Unsigned;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.sql.Date;


@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MessageID;

    private int ProfessorID;

    private String ProfessorName;

    private int StudentID;

    private String MessageTitle;

    private Date MessageDate;

    private String MessageBody;

    public Messages(){}

    public Messages(int MessageID, int StudentID, String MessageTitle, Date MessageDate, String MessageBody) {
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

    public int getProfessorID() {
        return ProfessorID;
    }

    public void setProfessorID(int professorID) {
        ProfessorID = professorID;
    }

    public String getProfessorName() {
        return ProfessorName;
    }

    public void setProfessorName(String professorName) {
        ProfessorName = professorName;
    }

    public String getMessageTitle() {
        return MessageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        MessageTitle = messageTitle;
    }

    public Date getMessageDate() {
        return MessageDate;
    }

    public void setMessageDate(Date messageDate) {
        MessageDate = messageDate;
    }

    public String getMessageBody() {
        return MessageBody;
    }

    public void setMessageBody(String messageBody) {
        MessageBody = messageBody;
    }
}

package com.cs157.StudentPortal.model;

import jdk.jfr.Unsigned;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Professors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ProfessorID;

    private String Name;

    private String Password;

    private String Department;

    public Professors() {}

    public Professors(int ProfessorID, String Name, String Password, String Department) {
        this.ProfessorID = ProfessorID;
        this.Name = Name;
        this.Password = Password;
        this.Department = Department;
    }

    public int getProfessorID() {
        return ProfessorID;
    }

    public void setProfessorID(int professorID) {
        ProfessorID = professorID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }
}

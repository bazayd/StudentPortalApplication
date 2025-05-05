package com.cs157.StudentPortal.repository;

import com.cs157.StudentPortal.model.Students;
import java.util.List;

public interface StudentsDAO {

    void addStudent(Students student);

    Students getStudentById(int StudentID);

    List<Students> getAllStudents();

    int deleteStudent(int StudentID);

    void updateStudent(Students student);
}

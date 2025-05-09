package com.cs157.StudentPortal.repository;

import com.cs157.StudentPortal.model.Grades;
import java.util.List;

public interface GradesDAO {

    List<Grades> findByStudentId(int StudentId);

    void updateGrades(int StudentId, int SectionId, String Grade);
}

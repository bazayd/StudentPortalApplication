package com.cs157.StudentPortal.repository;

import com.cs157.StudentPortal.model.Grades;
import java.util.List;

public interface GradesDAO {

    List<Grades> findByStudentId(int StudentId);

    List<Grades> findBySectionId(int SectionId);

    void updateGrades(int StudentId, int SectionId, String Grade);
}

package com.cs157.StudentPortal.repository;

import com.cs157.StudentPortal.model.Professors;
import java.util.List;

public interface ProfessorsDAO {

    void addProfessor(Professors professor);

    Professors getProfessorById(int ProfessorID);

    List<Professors> getAllProfessors();

    int deleteProfessor(int ProfessorID);

    void updateProfessor(Professors professor);
}

package com.cs157.StudentPortal.repository;

import com.cs157.StudentPortal.model.Students;
import java.util.List;

public interface StudentsDAO {

    Students findById(int StudentID);

    List<Students> findAll();

    int deleteById(int id);
}

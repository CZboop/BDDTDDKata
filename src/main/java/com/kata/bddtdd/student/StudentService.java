package com.kata.bddtdd.student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentDatabase studentDatabase;

    public StudentService(StudentDatabase studentDatabase){
        this.studentDatabase = studentDatabase;
    }

    public List<Student> getStudentDetails(String namePrefix) {
        return studentDatabase.getStudentDetailsFromDB(namePrefix.toLowerCase());
    }

    public Student getStudentWithExactLastName(String lastName) {
        return studentDatabase.getStudentExactMatch(lastName.toLowerCase()).orElseThrow(new RuntimeException("Not found"));
    }
}

package com.kata.bddtdd.student;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StudentDatabase {
    private List<Student> studentList;

    public StudentDatabase(){
        List<Student> studentList = new ArrayList<>();
        Student student = new Student("abhishek", "rajput");
        Student student1 = new Student("novita", "s");
        Student student2 = new Student("shin", "chan");
        Student student3 = new Student("ash", "ketchum");
        Student student4 = new Student("john", "cena");
        Student student5 = new Student("roger", "federer");
        Student student6 = new Student("david", "beckham");
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.add(student6);
        this.studentList = studentList;
    }

    public List<Student> getStudentDetailsFromDB(String namePrefix) {
        return studentList.stream().filter(
                studentDetails -> studentDetails.getFirstName().toLowerCase().startsWith(namePrefix)
        ).collect(Collectors.toList());
    }

    public Optional<Student> getStudentExactMatch(String lastName) {
        return studentList.stream().filter(
                studentDetails -> studentDetails.getLastName().toLowerCase() == lastName
        ).findFirst();
    }
}

package com.kata.bddtdd.studentTest;

import com.kata.bddtdd.student.Student;
import com.kata.bddtdd.student.StudentDatabase;
import com.kata.bddtdd.student.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    private StudentService studentService;
    private StudentDatabase mockStudentDatabase;

    @BeforeEach
    void setUp() {
        mockStudentDatabase = mock(StudentDatabase.class);
        studentService = new StudentService(mockStudentDatabase);
    }

    @Test
    @DisplayName("Get Student details for a valid name prefix")
    void getStudentDetails_whenNamePrefixIsPassed_shouldReturnStudentDetails() {
        //Arrange
        String namePrefix = "a";
        Student mockData = new Student("abhishek", "rajput");
        when(mockStudentDatabase.getStudentDetailsFromDB(namePrefix)).thenReturn(asList(mockData));

        //Action
        List<Student> actualStudentData = studentService.getStudentDetails(namePrefix);

        //Assertion
        assertAll("actualStudentData",
                () -> assertEquals("abhishek", actualStudentData.get(0).getFirstName()),
                () -> assertEquals("rajput", actualStudentData.get(0).getLastName())
        );
    }

    @Test
    void getStudentDetailsFromDB_ShouldBeCaseInsensitive(){
        //Arrange
        String namePrefix = "A";
        Student mockData = new Student("abhishek", "rajput");
        when(mockStudentDatabase.getStudentDetailsFromDB(namePrefix)).thenReturn(asList(mockData));

        //Action
        List<Student> actualStudentData = studentService.getStudentDetails(namePrefix);

        //Assertion
        assertAll("actualStudentData",
                () -> assertEquals("abhishek", actualStudentData.get(0).getFirstName()),
                () -> assertEquals("rajput", actualStudentData.get(0).getLastName())
        );
    }

    @Test
    void getStudentDetailsFromDB_ExactMatch(){
        //Arrange
        String name = "RaJpUt";
        Student mockData = new Student("abhishek", "rajput");
        when(mockStudentDatabase.getStudentDetailsFromDB(name)).thenReturn(asList(mockData));

        //Action
        List<Student> actualStudentData = studentService.getStudentDetails(name);

        //Assertion
        assertAll("actualStudentData",
                () -> assertEquals("abhishek", actualStudentData.get(0).getFirstName()),
                () -> assertEquals("rajput", actualStudentData.get(0).getLastName())
        );

    }



}
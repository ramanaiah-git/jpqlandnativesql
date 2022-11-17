package com.ramana.jpqlandnativesql;

import com.ramana.jpqlandnativesql.entity.Student;
import com.ramana.jpqlandnativesql.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class JpqlandnativesqlApplicationTests {


    @Autowired
    private StudentRepository repository;

    @Test
    public void saveStudent(){
        Student student = new Student();
        student.setFirstName("ramana");
        student.setLastName("pade");
        student.setScore(35);
        repository.save(student);

        Student student1 = new Student();
        student1.setFirstName("srinu");
        student1.setLastName("pade");
        student1.setScore(68);
        repository.save(student1);

        Student student2 = new Student();
        student2.setFirstName("suma");
        student2.setLastName("pade");
        student2.setScore(55);
        repository.save(student2);
    }

    @Test
    void testFindAll(){
       List<Student> students = repository.findAllStudents(PageRequest.of(0,1));
        System.out.println(students);
    }

    @Test
    void testFindAllStudentsPartial(){
        List<Object[]> students = repository.findAllStudentPartialData();
        for (Object[] obj: students){
            System.out.println(obj[0] + "        " + obj[1]);
        }
    }

    @Test
    void testFindAllStudentsByFirstName(){
        List<Student> ramana = repository.findAllStudentsByFirstName("ramana");
        System.out.println("First Name: " + ramana);
    }

    @Test
    void testFindStudentsByScore(){
        List<Student> score = repository.findStudentsByScore(20, 60);
        System.out.println(score);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testDeleteStudentsByFirstName(){
        repository.deleteStudentsByFirstName("ramana");
    }

    @Test
    void findAllStudentsNQ(){
        System.out.println(repository.findAllStudentsNQ());
    }
}

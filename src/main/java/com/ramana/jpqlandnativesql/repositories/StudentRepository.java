package com.ramana.jpqlandnativesql.repositories;

import com.ramana.jpqlandnativesql.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("FROM Student")
    List<Student> findAllStudents(Pageable pageable);

    @Query("SELECT st.firstName, st.lastName FROM Student st")
    List<Object[]> findAllStudentPartialData();

    @Query("FROM Student st WHERE st.firstName=:firstName")
    List<Student> findAllStudentsByFirstName(@Param("firstName") String firstName);

    @Query("FROM Student st WHERE st.score > :min and st.score < :max")
    List<Student> findStudentsByScore(@Param("min") int min,@Param("max") int max);

    @Modifying
    @Query("DELETE FROM Student st WHERE st.firstName = :firstName")
    void deleteStudentsByFirstName(@Param("firstName") String firstName);

    @Query(value = "SELECT * FROM  student", nativeQuery = true)
    List<Student> findAllStudentsNQ();

}

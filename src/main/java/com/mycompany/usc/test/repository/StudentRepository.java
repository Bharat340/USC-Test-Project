package com.mycompany.usc.test.repository;

import com.mycompany.usc.test.model.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pbharat
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByStudentId(Integer studentId);
    List<Student> findByDepartmentId(Integer departmentId);
}

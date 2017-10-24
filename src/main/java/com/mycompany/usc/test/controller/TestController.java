package com.mycompany.usc.test.controller;

import com.mycompany.usc.test.model.Student;
import com.mycompany.usc.test.repository.StudentRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pbharat
 */
@RestController
public class TestController {
    
    @Autowired
    private StudentRepository studentRepository;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/test", method = RequestMethod.GET, headers = "Accept=application/json")
    public String test() {
        log.info("=====================================Working========================================");
        return "Working";
    }
    
    @RequestMapping(value = "/studentTest", method = RequestMethod.GET, headers = "Accept=application/json")
    public Student studentTest() {
        log.info("=====================================Student========================================");
        Student student = new Student();
        this.studentRepository.save(student);

        Student student1 = this.studentRepository.findByStudentId(1);
        if (null == student1) {
            log.error("Data couldn't saved");
        }
        return student1;
    }
}

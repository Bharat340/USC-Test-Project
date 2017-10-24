package com.mycompany.usc.test.controller;

import com.google.gson.Gson;
import com.mycompany.usc.test.model.Expense;
import com.mycompany.usc.test.model.Student;
import com.mycompany.usc.test.service.StudentService;
import java.util.HashMap;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pbharat
 */
@RestController
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    Gson gson = new Gson();
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(value = "/student", method = RequestMethod.POST, headers = "Accept=application/json")
    public HashMap student(
            @RequestBody (required = false) String param) {
        HashMap<String, String> inputData = gson.fromJson(param, HashMap.class);
        Student student = this.studentService.addOrUpdateStudent(inputData);
        if (null == student) {
            return null;
        }
        HashMap<String, String> result = new HashMap<>();
        result.put("student_id", String.valueOf(student.getStudentId()));
        return result;
    }
    
    @RequestMapping(value = "/student/{studentId}", method = RequestMethod.GET)
    Student getStudent(@PathVariable("studentId") Integer studentId) {
        return this.studentService.fetchStudentInfo(studentId);
    }
    
    @RequestMapping(value = "/student/{studentId}/expense", method = RequestMethod.POST)
    public List<Expense> expenseUpdate(
            @RequestBody (required = false) List<String> param,
            @PathVariable("studentId") Integer studentId) {
        return this.studentService.addOrUpdateExpense(param, studentId);
    }

    @RequestMapping(value = "/dept/{departmentId}", method = RequestMethod.GET, headers =  "Accept=application/json")
    public HashMap getDepartmentDetails(
            @PathVariable("departmentId") Integer departmentId) {
        return this.studentService.getDepartmentDetails(departmentId);
    }
    
    @RequestMapping(value = "/student/{studentId}/expense/{expenseId}", method = RequestMethod.DELETE, headers =  "Accept=application/json")
    public HashMap deleteExpense(
            @PathVariable("studentId") Integer studentId,
            @PathVariable("expenseId") Integer expenseId) {
        return this.studentService.deleteExpense(studentId, expenseId);
    }
    
}

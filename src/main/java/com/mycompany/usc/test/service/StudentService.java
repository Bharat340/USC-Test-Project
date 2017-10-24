package com.mycompany.usc.test.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.usc.test.model.Department;
import com.mycompany.usc.test.model.Expense;
import com.mycompany.usc.test.model.Student;
import com.mycompany.usc.test.repository.DepartmentRepository;
import com.mycompany.usc.test.repository.ExpenseRepository;
import com.mycompany.usc.test.repository.StudentRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.collections.MapUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author pbharat
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    Gson gson = new Gson();
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(StudentService.class);

    public Student addOrUpdateStudent(HashMap<String, String> studentMap) {
        if (MapUtils.isEmpty(studentMap)) {
            log.error("Input data is empty or the json is invalid. " + gson.toJson(studentMap, HashMap.class));
            return null;
        }

        String name = studentMap.get("name");
        String email = studentMap.get("email");
        String phone = studentMap.get("phone");
        Boolean isDomestic = Boolean.getBoolean(studentMap.get("isDomestic"));
        Integer departmentId = studentMap.containsKey("departmentId") ? Integer.parseInt(studentMap.get("departmentId")) : 1;
        // Department 1 is not associated with any department. So this is a check
        Student student = new Student(name, email, phone, isDomestic, departmentId);
        student = this.studentRepository.save(student);
        return student;
    }

    public Student fetchStudentInfo(Integer studentId) {
        if (null == studentId) {
            log.error("Invalid input");
        }
        Student student = this.studentRepository.findByStudentId(studentId);
        if (null == student) {
            log.info("Student record not found for the studentId: " + studentId);
        }
        return student;
    }

    public List<Expense> addOrUpdateExpense(List<String> param, Integer studentId) {
        if (null == param || param.isEmpty()) {
            log.error("Blank or empty input");
            return null;
        }
        Gson customGson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
        List<Expense> expenses = new ArrayList<>();
        for (String json : param) {
            Expense expense = customGson.fromJson(json, Expense.class);
            expenses.add(expense);
        }
        if (expenses.isEmpty()) {
            log.error("Invalid input.");
            return null;
        }
        List<Expense> result = new ArrayList<>();
        for (Expense expense : expenses) {
            expense.setStudentId(studentId);
            expense = this.expenseRepository.save(expense);
            result.add(expense);
        }
        return result;
    }

    public HashMap getDepartmentDetails(Integer departmentId) {
        Department department = this.departmentRepository.findByDepartmentId(departmentId);
        if (null == department) {
            log.error("Department not found for id: " + departmentId);
            return null;
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("deptid", department.getDepartmentId());
        result.put("name", department.getName());
        List<Student> students = this.studentRepository.findByDepartmentId(departmentId);
        List<Expense> expenses = new ArrayList<>();
        for (Student student : students) {
            List<Expense> studentExpenses = this.expenseRepository.findByStudentId(student.getStudentId());
            expenses.addAll(studentExpenses);
        }
        result.put("expenses", expenses);
        return result;
    }

    public HashMap deleteExpense(Integer studentId, Integer expenseId) {
        Expense expense = this.expenseRepository.findByExpenseIdAndStudentId(expenseId, studentId);
        if (null == expense) {
            log.error("No expense exist for these details");
            return null;
        }
        this.expenseRepository.delete(expense);
        HashMap<String, String> result = new HashMap<>();
        result.put("status", "Success");
        return result;
    }
}

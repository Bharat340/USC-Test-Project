package com.mycompany.usc.test.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author pbharat
 */
@Entity
public class Expense implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private Integer expenseId;

    @Column
    private String title;

    @Column
    private Double amount;

    @Column
    private String description;

    @Column(columnDefinition = "int(11) NOT NULL DEFAULT 0")
    private Integer studentId;

    public Expense() {
    }

    public Expense(String title, Double amount, String description, Integer studentId) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.studentId = studentId;
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

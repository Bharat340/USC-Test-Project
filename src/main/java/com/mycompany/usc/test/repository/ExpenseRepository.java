package com.mycompany.usc.test.repository;

import com.mycompany.usc.test.model.Expense;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pbharat
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByStudentId(Integer studentId);
    Expense findByExpenseIdAndStudentId(Integer expenseId, Integer studentId);
}

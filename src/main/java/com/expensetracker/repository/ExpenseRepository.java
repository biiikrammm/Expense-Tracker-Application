package com.expensetracker.repository;

import com.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

   List<Expense> findByCategory(String category);

   List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

   List<Expense> findByCategoryAndDateBetween(String category, LocalDate startDate, LocalDate endDate);

   @Query("SELECT e.category, SUM(e.amount) FROM Expense e GROUP BY e.category")
   List<Object[]> getTotalByCategory();

   @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.date BETWEEN :startDate AND :endDate")
   BigDecimal getTotalExpensesBetweenDates(LocalDate startDate, LocalDate endDate);

   @Query("SELECT e.category, SUM(e.amount) FROM Expense e WHERE e.date BETWEEN :startDate AND :endDate GROUP BY e.category")
   List<Object[]> getCategoryTotalsBetweenDates(LocalDate startDate, LocalDate endDate);
}
package com.expensetracker.service;

import com.expensetracker.dto.CategoryReportDTO;
import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.dto.ExpenseReportDTO;
import com.expensetracker.entity.Expense;
import com.expensetracker.exception.ResourceNotFoundException;
import com.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

   private final ExpenseRepository expenseRepository;

   // Constructor for dependency injection
   public ExpenseService(ExpenseRepository expenseRepository) {
      this.expenseRepository = expenseRepository;
   }

   @Transactional
   public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
      Expense expense = mapToEntity(expenseDTO);
      Expense savedExpense = expenseRepository.save(expense);
      return mapToDTO(savedExpense);
   }

   public ExpenseDTO getExpenseById(Long id) {
      Expense expense = expenseRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
      return mapToDTO(expense);
   }

   public List<ExpenseDTO> getAllExpenses() {
      return expenseRepository.findAll().stream()
              .map(this::mapToDTO)
              .collect(Collectors.toList());
   }

   public List<ExpenseDTO> getExpensesByCategory(String category) {
      return expenseRepository.findByCategory(category).stream()
              .map(this::mapToDTO)
              .collect(Collectors.toList());
   }

   public List<ExpenseDTO> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
      return expenseRepository.findByDateBetween(startDate, endDate).stream()
              .map(this::mapToDTO)
              .collect(Collectors.toList());
   }

   @Transactional
   public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {
      Expense expense = expenseRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

      expense.setDescription(expenseDTO.getDescription());
      expense.setAmount(expenseDTO.getAmount());
      expense.setDate(expenseDTO.getDate());
      expense.setCategory(expenseDTO.getCategory());
      expense.setNotes(expenseDTO.getNotes());

      Expense updatedExpense = expenseRepository.save(expense);
      return mapToDTO(updatedExpense);
   }

   @Transactional
   public void deleteExpense(Long id) {
      if (!expenseRepository.existsById(id)) {
         throw new ResourceNotFoundException("Expense not found with id: " + id);
      }
      expenseRepository.deleteById(id);
   }

   public ExpenseReportDTO generateReport(LocalDate startDate, LocalDate endDate) {
      BigDecimal totalExpenses = expenseRepository.getTotalExpensesBetweenDates(startDate, endDate);
      if (totalExpenses == null) {
         totalExpenses = BigDecimal.ZERO;
      }

      List<Object[]> categoryTotals = expenseRepository.getCategoryTotalsBetweenDates(startDate, endDate);
      List<CategoryReportDTO> categoryBreakdown = new ArrayList<>();

      for (Object[] row : categoryTotals) {
         String category = (String) row[0];
         BigDecimal amount = (BigDecimal) row[1];

         Double percentage = 0.0;
         if (totalExpenses.compareTo(BigDecimal.ZERO) > 0) {
            percentage = amount.divide(totalExpenses, 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100))
                    .doubleValue();
         }

         categoryBreakdown.add(new CategoryReportDTO(category, amount, percentage));
      }

      return new ExpenseReportDTO(startDate, endDate, totalExpenses, categoryBreakdown);
   }

   private ExpenseDTO mapToDTO(Expense expense) {
      return new ExpenseDTO(
              expense.getId(),
              expense.getDescription(),
              expense.getAmount(),
              expense.getDate(),
              expense.getCategory(),
              expense.getNotes()
      );
   }

   private Expense mapToEntity(ExpenseDTO dto) {
      Expense expense = new Expense();
      expense.setDescription(dto.getDescription());
      expense.setAmount(dto.getAmount());
      expense.setDate(dto.getDate());
      expense.setCategory(dto.getCategory());
      expense.setNotes(dto.getNotes());
      return expense;
   }
}
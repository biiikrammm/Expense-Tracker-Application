package com.expensetracker.controller;

import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.dto.ExpenseReportDTO;
import com.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

   private final ExpenseService expenseService;

   // Constructor for dependency injection
   public ExpenseController(ExpenseService expenseService) {
      this.expenseService = expenseService;
   }

   @PostMapping
   public ResponseEntity<ExpenseDTO> createExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
      ExpenseDTO createdExpense = expenseService.createExpense(expenseDTO);
      return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
   }

   @GetMapping("/{id}")
   public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id) {
      ExpenseDTO expense = expenseService.getExpenseById(id);
      return ResponseEntity.ok(expense);
   }

   @GetMapping
   public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
      List<ExpenseDTO> expenses = expenseService.getAllExpenses();
      return ResponseEntity.ok(expenses);
   }

   @GetMapping("/category/{category}")
   public ResponseEntity<List<ExpenseDTO>> getExpensesByCategory(@PathVariable String category) {
      List<ExpenseDTO> expenses = expenseService.getExpensesByCategory(category);
      return ResponseEntity.ok(expenses);
   }

   @GetMapping("/date-range")
   public ResponseEntity<List<ExpenseDTO>> getExpensesByDateRange(
           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
      List<ExpenseDTO> expenses = expenseService.getExpensesByDateRange(startDate, endDate);
      return ResponseEntity.ok(expenses);
   }

   @PutMapping("/{id}")
   public ResponseEntity<ExpenseDTO> updateExpense(
           @PathVariable Long id,
           @Valid @RequestBody ExpenseDTO expenseDTO) {
      ExpenseDTO updatedExpense = expenseService.updateExpense(id, expenseDTO);
      return ResponseEntity.ok(updatedExpense);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
      expenseService.deleteExpense(id);
      return ResponseEntity.noContent().build();
   }

   @GetMapping("/report")
   public ResponseEntity<ExpenseReportDTO> generateReport(
           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
      ExpenseReportDTO report = expenseService.generateReport(startDate, endDate);
      return ResponseEntity.ok(report);
   }
}
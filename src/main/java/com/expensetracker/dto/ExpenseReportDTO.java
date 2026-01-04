package com.expensetracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ExpenseReportDTO {
   private LocalDate startDate;
   private LocalDate endDate;
   private BigDecimal totalExpenses;
   private List<CategoryReportDTO> categoryBreakdown;

   // Default constructor
   public ExpenseReportDTO() {
   }

   // All-args constructor
   public ExpenseReportDTO(LocalDate startDate, LocalDate endDate, BigDecimal totalExpenses, List<CategoryReportDTO> categoryBreakdown) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.totalExpenses = totalExpenses;
      this.categoryBreakdown = categoryBreakdown;
   }

   // Getters and Setters
   public LocalDate getStartDate() {
      return startDate;
   }

   public void setStartDate(LocalDate startDate) {
      this.startDate = startDate;
   }

   public LocalDate getEndDate() {
      return endDate;
   }

   public void setEndDate(LocalDate endDate) {
      this.endDate = endDate;
   }

   public BigDecimal getTotalExpenses() {
      return totalExpenses;
   }

   public void setTotalExpenses(BigDecimal totalExpenses) {
      this.totalExpenses = totalExpenses;
   }

   public List<CategoryReportDTO> getCategoryBreakdown() {
      return categoryBreakdown;
   }

   public void setCategoryBreakdown(List<CategoryReportDTO> categoryBreakdown) {
      this.categoryBreakdown = categoryBreakdown;
   }
}
package com.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseDTO {

   private Long id;

   @NotBlank(message = "Description is required")
   private String description;

   @NotNull(message = "Amount is required")
   @Positive(message = "Amount must be positive")
   private BigDecimal amount;

   @NotNull(message = "Date is required")
   private LocalDate date;

   @NotBlank(message = "Category is required")
   private String category;

   private String notes;

   // Default constructor
   public ExpenseDTO() {
   }

   // All-args constructor
   public ExpenseDTO(Long id, String description, BigDecimal amount, LocalDate date, String category, String notes) {
      this.id = id;
      this.description = description;
      this.amount = amount;
      this.date = date;
      this.category = category;
      this.notes = notes;
   }

   // Getters and Setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public BigDecimal getAmount() {
      return amount;
   }

   public void setAmount(BigDecimal amount) {
      this.amount = amount;
   }

   public LocalDate getDate() {
      return date;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public String getNotes() {
      return notes;
   }

   public void setNotes(String notes) {
      this.notes = notes;
   }
}
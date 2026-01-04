package com.expensetracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotBlank(message = "Description is required")
   @Column(nullable = false)
   private String description;

   @NotNull(message = "Amount is required")
   @Positive(message = "Amount must be positive")
   @Column(nullable = false, precision = 10, scale = 2)
   private BigDecimal amount;

   @NotNull(message = "Date is required")
   @Column(nullable = false)
   private LocalDate date;

   @NotBlank(message = "Category is required")
   @Column(nullable = false)
   private String category;

   @Column(length = 500)
   private String notes;

   @Column(name = "created_at", updatable = false)
   private LocalDate createdAt;

   @Column(name = "updated_at")
   private LocalDate updatedAt;

   @PrePersist
   protected void onCreate() {
      createdAt = LocalDate.now();
      updatedAt = LocalDate.now();
   }

   @PreUpdate
   protected void onUpdate() {
      updatedAt = LocalDate.now();
   }

   // Default constructor
   public Expense() {
   }

   // All-args constructor
   public Expense(Long id, String description, BigDecimal amount, LocalDate date, String category, String notes, LocalDate createdAt, LocalDate updatedAt) {
      this.id = id;
      this.description = description;
      this.amount = amount;
      this.date = date;
      this.category = category;
      this.notes = notes;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
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

   public LocalDate getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDate createdAt) {
      this.createdAt = createdAt;
   }

   public LocalDate getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(LocalDate updatedAt) {
      this.updatedAt = updatedAt;
   }
}
package com.expensetracker.dto;

import java.math.BigDecimal;

public class CategoryReportDTO {
   private String category;
   private BigDecimal totalAmount;
   private Double percentage;

   // Default constructor
   public CategoryReportDTO() {
   }

   // All-args constructor
   public CategoryReportDTO(String category, BigDecimal totalAmount, Double percentage) {
      this.category = category;
      this.totalAmount = totalAmount;
      this.percentage = percentage;
   }

   // Getters and Setters
   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public BigDecimal getTotalAmount() {
      return totalAmount;
   }

   public void setTotalAmount(BigDecimal totalAmount) {
      this.totalAmount = totalAmount;
   }

   public Double getPercentage() {
      return percentage;
   }

   public void setPercentage(Double percentage) {
      this.percentage = percentage;
   }
}
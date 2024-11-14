package com.assessment.twinleaves.dto;
import java.time.LocalDate;

public class ProductRequest {

    private String productName;
    private LocalDate createdOn = LocalDate.now();

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }
}

package com.hussey.invoiceservice.model;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Objects;

public class Invoice {
    // Properties
    private Integer invoiceId;

    @NotNull(message = "customerId must not be null.")
    @Positive(message = "customerId must be a positive number between 1 and 2,147,483,647.")
    private Integer customerId;

    @Null(message = "The purchase date must remain empty.")
    private LocalDate purchaseDate;

    // Constructors
    public Invoice() {

    }

    public Invoice(Integer customerId, LocalDate purchaseDate) {
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
    }

    public Invoice(Integer invoiceId, Integer customerId, LocalDate purchaseDate) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
    }

    // Getters
    public Integer getInvoiceId() {
        return this.invoiceId;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public LocalDate getPurchaseDate() {
        return this.purchaseDate;
    }

    // Setters
    public void setInvoiceId(Integer invoiceIdIn) {
        this.invoiceId = invoiceIdIn;
    }

    public void setCustomerId(Integer customerIdIn) {
        this.customerId = customerIdIn;
    }

    public void setPurchaseDate(LocalDate purchaseDateIn) {
        this.purchaseDate = purchaseDateIn;
    }

    // equals(), hashCode(), and toString()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceId, invoice.invoiceId) &&
                customerId.equals(invoice.customerId) &&
                purchaseDate.equals(invoice.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, customerId, purchaseDate);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", customerId=" + customerId +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}

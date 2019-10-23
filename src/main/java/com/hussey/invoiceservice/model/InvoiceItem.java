package com.hussey.invoiceservice.model;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItem {
    // Properties
    private Integer invoiceItemId;

    @NotNull(message = "Invoice ID must not be null.")
    @Positive(message = "Invoice ID must be a positive integer number between 1 and 2,146,483,647.")
    private Integer invoiceId;

    @NotNull(message = "Inventory ID must not be null.")
    @Positive(message = "Inventory ID must be a positive integer number between 1 and 2,146,483,647.")
    private Integer inventoryId;

    @NotNull(message = "Quantity must not be null.")
    @PositiveOrZero(message = "Quantity must be an integer number between 0 and 2,146,483,647.")
    private Integer quantity;

    @NotNull(message = "Unit Price must not be null.")
    @Positive(message = "Unit Price must be positive.")
    @Digits(integer = 5, fraction = 2, message = "Unit Price was in an incorrect format.")
    private BigDecimal unitPrice;

    // Constructors
    public InvoiceItem() {

    }

    public InvoiceItem(Integer invoiceId, Integer inventoryId, Integer quantity, BigDecimal unitPrice) {
        this.invoiceId = invoiceId;
        this.inventoryId = inventoryId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public InvoiceItem(Integer invoiceItemId, Integer invoiceId, Integer inventoryId, Integer quantity, BigDecimal unitPrice) {
        this.invoiceItemId = invoiceItemId;
        this.invoiceId = invoiceId;
        this.inventoryId = inventoryId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Getters
    public Integer getInvoiceItemId() {
        return this.invoiceItemId;
    }

    public Integer getInvoiceId() {
        return this.invoiceId;
    }

    public Integer getInventoryId() {
        return this.inventoryId;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    // Setters
    public void setInvoiceItemId(Integer invoiceItemIdIn) {
        this.invoiceItemId = invoiceItemIdIn;
    }

    public void setInvoiceId(Integer invoiceIdIn) {
        this.invoiceId = invoiceIdIn;
    }

    public void setInventoryId(Integer inventoryIdIn) {
        this.inventoryId = inventoryIdIn;
    }

    public void setQuantity(Integer quantityIn) {
        this.quantity = quantityIn;
    }

    public void setUnitPrice(BigDecimal unitPriceIn) {
        this.unitPrice = unitPriceIn;
    }

    // equals(), hashCode(), and toString()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return Objects.equals(invoiceItemId, that.invoiceItemId) &&
                invoiceId.equals(that.invoiceId) &&
                inventoryId.equals(that.inventoryId) &&
                quantity.equals(that.quantity) &&
                unitPrice.equals(that.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, invoiceId, inventoryId, quantity, unitPrice);
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "invoiceItemId=" + invoiceItemId +
                ", invoiceId=" + invoiceId +
                ", inventoryId=" + inventoryId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}

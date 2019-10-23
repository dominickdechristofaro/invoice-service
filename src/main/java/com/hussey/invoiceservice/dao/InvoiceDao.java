package com.hussey.invoiceservice.dao;
import com.hussey.invoiceservice.model.Invoice;
import java.util.List;

public interface InvoiceDao {
    Invoice addInvoice(Invoice invoice);
    Invoice getInvoice(int invoiceId);
    List<Invoice> getAllInvoice();
    List<Invoice> getInvoiceByCustomerId(int customerId);
    void updateInvoice(Invoice invoice);
    void deleteInvoice(int invoiceId);
}

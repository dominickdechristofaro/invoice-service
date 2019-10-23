package com.hussey.invoiceservice.service;
import com.hussey.invoiceservice.model.Invoice;
import java.util.List;

public interface InvoiceService {
    Invoice addInvoice(Invoice invoice);
    Invoice getInvoice(int invoiceId);
    List<Invoice> getAllInvoice();
    List<Invoice> getInvoiceByCustomerId(int customerId);
    void updateInvoice(Invoice invoice);
    void deleteInvoice(int invoiceId);
}

package com.hussey.invoiceservice.service;
import com.hussey.invoiceservice.model.InvoiceItem;
import java.util.List;

public interface InvoiceItemService {
    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);
    InvoiceItem getInvoiceItem(int invoiceItemId);
    List<InvoiceItem> getAllInvoiceItem();
    List<InvoiceItem> getInvoiceItemByInvoiceId(int invoiceId);
    List<InvoiceItem> getInvoiceItemByInventoryId(int inventoryId);
    void updateInvoiceItem(InvoiceItem invoiceItem);
    void deleteInvoiceItem(int invoiceItemId);
}

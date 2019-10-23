package com.hussey.invoiceservice.service;
import com.hussey.invoiceservice.dao.InvoiceItemDao;
import com.hussey.invoiceservice.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class InvoiceItemServiceImpl implements InvoiceItemService {
    // Properties
    private InvoiceItemDao invoiceItemDao;

    // Constructor
    @Autowired
    public InvoiceItemServiceImpl(InvoiceItemDao invoiceItemDao) {
        this.invoiceItemDao = invoiceItemDao;
    }

    // Methods
    @Override
    @Transactional
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {
        return invoiceItemDao.addInvoiceItem(invoiceItem);
    }

    @Override
    public InvoiceItem getInvoiceItem(int invoiceItemId) {
        return invoiceItemDao.getInvoiceItem(invoiceItemId);
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItem() {
        return invoiceItemDao.getAllInvoiceItem();
    }

    @Override
    public List<InvoiceItem> getInvoiceItemByInvoiceId(int invoiceId) {
        return invoiceItemDao.getInvoiceItemByInvoiceId(invoiceId);
    }

    @Override
    public List<InvoiceItem> getInvoiceItemByInventoryId(int inventoryId) {
        return invoiceItemDao.getInvoiceItemByInventoryId(inventoryId);
    }

    @Override
    @Transactional
    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        invoiceItemDao.updateInvoiceItem(invoiceItem);
    }

    @Override
    @Transactional
    public void deleteInvoiceItem(int invoiceItemId) {
        invoiceItemDao.deleteInvoiceItem(invoiceItemId);
    }
}

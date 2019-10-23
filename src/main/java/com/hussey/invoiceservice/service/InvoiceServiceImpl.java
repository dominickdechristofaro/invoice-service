package com.hussey.invoiceservice.service;
import com.hussey.invoiceservice.dao.InvoiceDao;
import com.hussey.invoiceservice.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Component
public class InvoiceServiceImpl implements InvoiceService {
    // Properties
    private InvoiceDao invoiceDao;

    // Constructor
    @Autowired
    public InvoiceServiceImpl(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    // Methods
    @Override
    @Transactional
    public Invoice addInvoice(Invoice invoice) {
        invoice.setPurchaseDate(LocalDate.now());
        return invoiceDao.addInvoice(invoice);
    }

    @Override
    public Invoice getInvoice(int invoiceId) {
        return invoiceDao.getInvoice(invoiceId);
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return invoiceDao.getAllInvoice();
    }

    @Override
    public List<Invoice> getInvoiceByCustomerId(int customerId) {
        return invoiceDao.getInvoiceByCustomerId(customerId);
    }

    @Override
    @Transactional
    public void updateInvoice(Invoice invoice) {
        invoiceDao.updateInvoice(invoice);
    }

    @Override
    @Transactional
    public void deleteInvoice(int invoiceId) {
        invoiceDao.deleteInvoice(invoiceId);
    }
}

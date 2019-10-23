package com.hussey.invoiceservice.dao;
import com.hussey.invoiceservice.model.Invoice;
import com.hussey.invoiceservice.model.InvoiceItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class InvoiceDaoTest {
    // Autowired Dao
    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private InvoiceItemDao invoiceItemDao;

    // setUp()
    @BeforeEach
    public void setUp() throws Exception {
        // Clean up the Invoice Item table in the database
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItem();
        invoiceItemList.forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));

        // Clean up the Invoice table in the database
        List<Invoice> invoiceList = invoiceDao.getAllInvoice();
        invoiceList.forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoiceId()));
    }

    // Tests
    @Test
    public void addInvoice() {
        // Add a new Invoice object to the database
        Invoice invoice = new Invoice(1, LocalDate.now());
        invoice = invoiceDao.addInvoice(invoice);

        // Create a copy of the newly added Invoice object
        Invoice invoiceCopy = invoiceDao.getInvoice(invoice.getInvoiceId());

        // Test addInvoice() method
        assertEquals(invoice, invoiceCopy);
    }

    @Test
    public void getInvoice() {
        // Add a new Invoice object to the database
        Invoice invoice = new Invoice(1, LocalDate.now());
        invoice = invoiceDao.addInvoice(invoice);

        // Create a copy of the newly added Invoice object
        Invoice invoiceCopy = invoiceDao.getInvoice(invoice.getInvoiceId());

        // Test getInvoice() method
        assertEquals(invoice, invoiceCopy);
    }

    @Test
    public void getAllInvoice() {
        // Add 2 invoice objects to the database
        Invoice invoice1 = new Invoice(1, LocalDate.now());
        Invoice invoice2 = new Invoice(2, LocalDate.now());
        invoice1 = invoiceDao.addInvoice(invoice1);
        invoice2 = invoiceDao.addInvoice(invoice2);

        // Get a List of all the Invoice objects in the database
        List<Invoice> invoiceList = invoiceDao.getAllInvoice();

        // Test getAllInvoice() method
        assertEquals(2, invoiceList.size());
    }

    @Test
    public void getInvoiceByCustomerId() {
        // Add 3 invoice objects to the database
        Invoice invoice1 = new Invoice(1, LocalDate.now());
        Invoice invoice2 = new Invoice(2, LocalDate.now());
        Invoice invoice3 = new Invoice(2, LocalDate.now());
        invoice1 = invoiceDao.addInvoice(invoice1);
        invoice2 = invoiceDao.addInvoice(invoice2);
        invoice3 = invoiceDao.addInvoice(invoice3);

        // Get a List of Invoice objects by customer id
        List<Invoice> customerId1InvoiceList = invoiceDao.getInvoiceByCustomerId(1);
        List<Invoice> customerId2InvoiceList = invoiceDao.getInvoiceByCustomerId(2);

        // Test getInvoiceByCustomerId() method
        assertEquals(1, customerId1InvoiceList.size());
        assertEquals(2, customerId2InvoiceList.size());
    }

    @Test
    public void updateInvoice() {
        // Add an Invoice object to the database
        Invoice invoice = new Invoice(1, LocalDate.now());
        invoice = invoiceDao.addInvoice(invoice);

        // Update the invoice in the database
        invoice.setCustomerId(2);
        invoiceDao.updateInvoice(invoice);

        // Get a copy of the update invoice object
        Invoice invoiceCopy = invoiceDao.getInvoice(invoice.getInvoiceId());

        // Test updateInvoice() method
        assertEquals(invoice, invoiceCopy);
    }

    @Test
    public void deleteInvoice() {
        // Add an Invoice object to the database
        Invoice invoice = new Invoice(1, LocalDate.now());
        invoice = invoiceDao.addInvoice(invoice);

        // Delete the invoice object from the database
        invoiceDao.deleteInvoice(invoice.getInvoiceId());

        // Get a copy of the deleted invoice object
        Invoice invoiceCopy = invoiceDao.getInvoice(invoice.getInvoiceId());

        // Test deleteInvoice() method
        assertNull(invoiceCopy);
    }
}

package com.hussey.invoiceservice.service;
import com.hussey.invoiceservice.dao.InvoiceDao;
import com.hussey.invoiceservice.dao.InvoiceDaoJdbcTemplateImpl;
import com.hussey.invoiceservice.model.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

public class InvoiceServiceTest {
    // Properties
    private InvoiceServiceImpl invoiceService;
    private ArgumentCaptor<Invoice> invoiceArgumentCaptor = ArgumentCaptor.forClass(Invoice.class);
    private ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
    private InvoiceDao invoiceDao;

    // Before
    @BeforeEach
    public void setUp() throws Exception {
        setUpInvoiceDaoMock();
        invoiceService = new InvoiceServiceImpl(invoiceDao);
    }

    @Test
    public void addInvoice() {
        // Add Invoice to the database
        Invoice invoice = new Invoice(1);
        invoice = invoiceService.addInvoice(invoice);

        // Copy the Invoice object added to the database
        Invoice invoiceCopy = invoiceService.getInvoice(invoice.getInvoiceId());

        // Test the addInvoice() method
        Mockito.verify(invoiceDao, times(1)).getInvoice(invoice.getInvoiceId());
        assertEquals(invoice, invoiceCopy);
    }

    @Test
    public void getInvoice() {
        // Add Invoice to the database
        Invoice invoice = new Invoice(1);
        invoice = invoiceService.addInvoice(invoice);

        // Copy the Invoice object added to the database
        Invoice invoiceCopy = invoiceService.getInvoice(invoice.getInvoiceId());

        // Test the getInvoice() method
        Mockito.verify(invoiceDao, times(1)).getInvoice(invoice.getInvoiceId());
        assertEquals(invoice, invoiceCopy);
    }

    @Test
    public void getAllInvoice() {
        // Get all Invoices to the database
        List<Invoice> allInvoiceList = invoiceService.getAllInvoice();

        // Test the getAllInvoice() method
        assertEquals(3, allInvoiceList.size());
    }

    @Test
    public void getInvoiceByCustomerId() {
        // Get a list of all Invoices in the database by customerId
        List<Invoice> customerId1InvoiceList = invoiceService.getInvoiceByCustomerId(1);
        List<Invoice> customerId2InvoiceList = invoiceService.getInvoiceByCustomerId(2);

        // Test the getInvoiceByCustomerId() method
        assertEquals(2, customerId1InvoiceList.size());
        assertEquals(1, customerId2InvoiceList.size());

    }

    @Test
    public void updateInvoice() {
        // Add Invoice to the database
        Invoice invoice = new Invoice(1);
        invoice = invoiceService.addInvoice(invoice);

        // Update the Invoice in the database
        invoice.setCustomerId(2);
        invoiceService.updateInvoice(invoice);

        // Test the updateInvoice() method
        Mockito.verify(invoiceDao, times(1)).updateInvoice(invoiceArgumentCaptor.getValue());
        assertEquals(invoice, invoiceArgumentCaptor.getValue());
    }

    @Test
    public void deleteInvoice() {
        // Add Invoice to the database
        Invoice invoice = new Invoice(1);
        invoice = invoiceService.addInvoice(invoice);

        // Delete the invoice from the database
        invoiceService.deleteInvoice(invoice.getInvoiceId());

        // Test the deleteInvoice() method
        Mockito.verify(invoiceDao, times(1)).deleteInvoice(integerArgumentCaptor.getValue());
        assertEquals(invoice.getInvoiceId(), integerArgumentCaptor.getValue());
    }

    // InvoiceDao Mock
    private void setUpInvoiceDaoMock() {
        // Set up the InvoiceDao Mock
        invoiceDao = Mockito.mock(InvoiceDaoJdbcTemplateImpl.class);

        // Set up Invoice Input & Responses
        Invoice invoiceInput1 = new Invoice(1, LocalDate.now());
        Invoice invoiceInput2 = new Invoice(1, LocalDate.now());
        Invoice invoiceInput3 = new Invoice(2, LocalDate.now());
        Invoice invoiceResponse1 = new Invoice(1, 1, LocalDate.now());
        Invoice invoiceResponse2 = new Invoice(2, 1, LocalDate.now());
        Invoice invoiceResponse3 = new Invoice(3, 2, LocalDate.now());

        // Set up all Invoice List
        List<Invoice> allInvoiceList = new ArrayList<>();
        allInvoiceList.add(invoiceResponse1);
        allInvoiceList.add(invoiceResponse2);
        allInvoiceList.add(invoiceResponse3);

        // Set up Invoice by Customer ID Lists
        List<Invoice> customerId1InvoiceList = new ArrayList<>();
        List<Invoice> customerId2InvoiceList = new ArrayList<>();
        customerId1InvoiceList.add(invoiceResponse1);
        customerId1InvoiceList.add(invoiceResponse2);
        customerId2InvoiceList.add(invoiceResponse3);

        // Mocking addInvoice() DAO method
        Mockito.doReturn(invoiceResponse1).when(invoiceDao).addInvoice(invoiceInput1);
        Mockito.doReturn(invoiceResponse2).when(invoiceDao).addInvoice(invoiceInput2);
        Mockito.doReturn(invoiceResponse3).when(invoiceDao).addInvoice(invoiceInput3);

        // Mocking getInvoice() DAO method
        Mockito.doReturn(invoiceResponse1).when(invoiceDao).getInvoice(1);
        Mockito.doReturn(invoiceResponse2).when(invoiceDao).getInvoice(2);
        Mockito.doReturn(invoiceResponse3).when(invoiceDao).getInvoice(3);

        // Mocking getAllInvoice() DAO method
        Mockito.doReturn(allInvoiceList).when(invoiceDao).getAllInvoice();

        // Mocking getInvoiceByCustomerId() DAO method
        Mockito.doReturn(customerId1InvoiceList).when(invoiceDao).getInvoiceByCustomerId(1);
        Mockito.doReturn(customerId2InvoiceList).when(invoiceDao).getInvoiceByCustomerId(2);

        // Mocking updateInvoice() DAO method
        Mockito.doNothing().when(invoiceDao).updateInvoice(invoiceArgumentCaptor.capture());

        // Mocking deleteInvoice() DAO method
        Mockito.doNothing().when(invoiceDao).deleteInvoice(integerArgumentCaptor.capture());
    }
}

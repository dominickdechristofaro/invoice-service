package com.hussey.invoiceservice.controller;
import com.hussey.invoiceservice.model.Invoice;
import com.hussey.invoiceservice.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceControllerImpl implements InvoiceController {
    // Properties
    @Autowired
    private InvoiceServiceImpl invoiceService;

    // Constructor
    public InvoiceControllerImpl() {

    }

    // Methods
    @Override
    @Transactional
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody @Valid Invoice invoice) {
        return invoiceService.addInvoice(invoice);
    }

    @Override
    public Invoice getInvoice(int invoiceId) {
        return null;
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return null;
    }

    @Override
    public List<Invoice> getInvoiceByCustomerId(int customerId) {
        return null;
    }

    @Override
    @Transactional
    public void updateInvoice(Invoice invoice) {

    }

    @Override
    @Transactional
    public void deleteInvoice(int invoiceId) {

    }
}

package com.hussey.invoiceservice.controller;
import com.hussey.invoiceservice.model.InvoiceItem;
import com.hussey.invoiceservice.service.InvoiceItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceItemControllerImpl implements InvoiceItemController {
    // Properties
    @Autowired
    private InvoiceItemServiceImpl invoiceItemService;

    // Constructors
    public InvoiceItemControllerImpl() {

    }

    // Controller Methods
    @Override
    @Transactional
    @RequestMapping(value = "/invoiceItem", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceItem addInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        return invoiceItemService.addInvoiceItem(invoiceItem);
    }

    @Override
    @RequestMapping(value = "/invoiceItem/{invoiceItemId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceItem getInvoiceItem(@PathVariable int invoiceItemId) {
        // Get the request InvoiceItem from the database
        InvoiceItem invoiceItemFromDB = invoiceItemService.getInvoiceItem(invoiceItemId);

        // Return the InvoiceItem if it is found in the database
        if(invoiceItemFromDB != null) {
            return invoiceItemFromDB;
        } else {
            throw new IllegalArgumentException("There is no InvoiceItem with the id: " + invoiceItemId);
        }
    }

    @Override
    @RequestMapping(value = "/invoiceItem", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceItem> getAllInvoiceItem() {
        // Get all InvoiceItems in the database
        return invoiceItemService.getAllInvoiceItem();
    }

    @Override
    @RequestMapping(value = "/invoiceItem/byInvoice/{invoiceId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceItem> getInvoiceItemByInvoiceId(@PathVariable int invoiceId) {
        // Get all InvoiceItem in the database by a invoiceId
        return invoiceItemService.getInvoiceItemByInvoiceId(invoiceId);
    }

    @Override
    @RequestMapping(value = "/invoiceItem/byInventory/{inventoryId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceItem> getInvoiceItemByInventoryId(@PathVariable int inventoryId) {
        // Get all InvoiceItem in the database by inventoryId
        return invoiceItemService.getInvoiceItemByInventoryId(inventoryId);
    }

    @Override
    @Transactional
    @RequestMapping(value = "/invoiceItem", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        // Validate that the InvoiceItem object has an Id
        if(invoiceItem.getInvoiceItemId() == null) {
            throw new IllegalArgumentException("You must pass an invoiceItemId when trying to update.");
        }

        // Get the InvoiceItem object from the database
        InvoiceItem invoiceItemFromDB = invoiceItemService.getInvoiceItem(invoiceItem.getInvoiceItemId());

        // Update the InvoiceItem if it is found in the database
        if(invoiceItemFromDB != null) {
            invoiceItemService.updateInvoiceItem(invoiceItem);
        } else {
            throw new IllegalArgumentException("There is no InvoiceItem with id: " + invoiceItem.getInvoiceItemId());
        }
    }

    @Override
    @Transactional
    @RequestMapping(value = "/invoiceItem/{invoiceItemId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteInvoiceItem(@PathVariable int invoiceItemId) {
        // Get the InvoiceItem from the database
        InvoiceItem invoiceItemFromDB = invoiceItemService.getInvoiceItem(invoiceItemId);

        // Delete the InvoiceItem if it is found in the database
        if(invoiceItemFromDB != null) {
            invoiceItemService.deleteInvoiceItem(invoiceItemId);
        } else {
            throw new IllegalArgumentException("There is no InvoiceItem with id: " + invoiceItemId);
        }
    }
}

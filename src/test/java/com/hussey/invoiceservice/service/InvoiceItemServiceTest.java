package com.hussey.invoiceservice.service;
import com.hussey.invoiceservice.dao.InvoiceItemDao;
import com.hussey.invoiceservice.dao.InvoiceItemDaoJdbcTemplateImpl;
import com.hussey.invoiceservice.model.InvoiceItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

public class InvoiceItemServiceTest {
    // Properties
    private InvoiceItemServiceImpl invoiceItemService;
    private ArgumentCaptor<InvoiceItem> invoiceItemArgumentCaptor = ArgumentCaptor.forClass(InvoiceItem.class);
    private ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
    private InvoiceItemDao invoiceItemDao;

    // Before
    @BeforeEach
    public void setUp() throws Exception {
        setUpInvoiceItemDaoMock();
        invoiceItemService = new InvoiceItemServiceImpl(invoiceItemDao);
    }

    // Tests
    @Test
    public void addInvoiceItem() {
        // Add invoiceItem to the database
        InvoiceItem invoiceItem = new InvoiceItem(1, 1, 10, new BigDecimal("9.99"));
        invoiceItem = invoiceItemService.addInvoiceItem(invoiceItem);

        // Copy the InvoiceItem object added to the database
        InvoiceItem invoiceItemCopy = invoiceItemService.getInvoiceItem(invoiceItem.getInvoiceItemId());

        // Test the addInvoiceItem() method
        Mockito.verify(invoiceItemDao, times(1)).getInvoiceItem(invoiceItem.getInvoiceItemId());
        assertEquals(invoiceItem, invoiceItemCopy);
    }

    @Test
    public void getInvoiceItem() {
        // Add invoiceItem to the database
        InvoiceItem invoiceItem = new InvoiceItem(1, 1, 10, new BigDecimal("9.99"));
        invoiceItem = invoiceItemService.addInvoiceItem(invoiceItem);

        // Copy the InvoiceItem object added to the database
        InvoiceItem invoiceItemCopy = invoiceItemService.getInvoiceItem(invoiceItem.getInvoiceItemId());

        // Test the getInvoiceItem() method
        Mockito.verify(invoiceItemDao, times(1)).getInvoiceItem(invoiceItem.getInvoiceItemId());
        assertEquals(invoiceItem, invoiceItemCopy);
    }

    @Test
    public void getAllInvoiceItem() {
        // Get all InvoiceItems from the database
        List<InvoiceItem> allInvoiceItemList = invoiceItemService.getAllInvoiceItem();

        // Test the getAllInvoiceItem() method
        assertEquals(3, allInvoiceItemList.size());
    }

    @Test
    public void getInvoiceItemByInvoiceId() {
        // Get a List of all InvoiceItems in the database by InvoiceId
        List<InvoiceItem> invoiceId1InvoiceItemList = invoiceItemService.getInvoiceItemByInvoiceId(1);
        List<InvoiceItem> invoiceId2InvoiceItemList = invoiceItemService.getInvoiceItemByInvoiceId(2);

        // Test the getInvoiceItemByInvoiceId() method
        assertEquals(2, invoiceId1InvoiceItemList.size());
        assertEquals(1, invoiceId2InvoiceItemList.size());
    }

    @Test
    public void getInvoiceItemByInventoryId() {
        // Get a List of all InvoiceItems in the database by InventoryId
        List<InvoiceItem> inventoryId1InvoiceItemList = invoiceItemService.getInvoiceItemByInventoryId(1);
        List<InvoiceItem> inventoryId2InvoiceItemList = invoiceItemService.getInvoiceItemByInventoryId(2);

        // Test the getInvoiceItemByInventoryId() method
        assertEquals(1, inventoryId1InvoiceItemList.size());
        assertEquals(2, inventoryId2InvoiceItemList.size());

    }

    @Test
    public void updateInvoiceItem() {
        // Add InvoiceItem to the database
        InvoiceItem invoiceItem = new InvoiceItem(1, 1, 10, new BigDecimal("9.99"));
        invoiceItem = invoiceItemService.addInvoiceItem(invoiceItem);

        // Update the Invoice in the database
        invoiceItem.setQuantity(14);
        invoiceItemService.updateInvoiceItem(invoiceItem);

        // Test the updateInvoiceItem() method
        Mockito.verify(invoiceItemDao, times(1)).updateInvoiceItem(invoiceItemArgumentCaptor.getValue());
        assertEquals(invoiceItem, invoiceItemArgumentCaptor.getValue());
    }

    @Test
    public void deleteInvoiceItem() {
        // Add InvoiceItem to the database
        InvoiceItem invoiceItem = new InvoiceItem(1, 1, 10, new BigDecimal("9.99"));
        invoiceItem = invoiceItemService.addInvoiceItem(invoiceItem);

        // Delete the invoice item from the database
        invoiceItemService.deleteInvoiceItem(invoiceItem.getInvoiceItemId());

        // Test the deleteInvoiceItem() method
        Mockito.verify(invoiceItemDao, times(1)).deleteInvoiceItem(integerArgumentCaptor.getValue());
        assertEquals(invoiceItem.getInvoiceItemId(), integerArgumentCaptor.getValue());
    }

    private void setUpInvoiceItemDaoMock() {
        // Set up the InvoiceItemDao Mock
        invoiceItemDao = Mockito.mock(InvoiceItemDaoJdbcTemplateImpl.class);

        // Set up InvoiceItem Input and Responses
        InvoiceItem invoiceItemInput1 = new InvoiceItem(1, 1, 10, new BigDecimal("9.99"));
        InvoiceItem invoiceItemInput2 = new InvoiceItem(1, 2, 20, new BigDecimal("14.99"));
        InvoiceItem invoiceItemInput3 = new InvoiceItem(2, 2, 30, new BigDecimal("19.99"));
        InvoiceItem invoiceItemResponse1 = new InvoiceItem(1, 1, 1, 10, new BigDecimal("9.99"));
        InvoiceItem invoiceItemResponse2 = new InvoiceItem(2, 1, 2, 20, new BigDecimal("14.99"));
        InvoiceItem invoiceItemResponse3 = new InvoiceItem(3, 2, 2, 30, new BigDecimal("19.99"));

        // Set up the all InvoiceItem List
        List<InvoiceItem> allInvoiceItemList = new ArrayList<>();
        allInvoiceItemList.add(invoiceItemResponse1);
        allInvoiceItemList.add(invoiceItemResponse2);
        allInvoiceItemList.add(invoiceItemResponse3);

        // Set up InvoiceItem Lists by InvoiceId
        List<InvoiceItem> invoiceId1InvoiceItemList = new ArrayList<>();
        List<InvoiceItem> invoiceId2InvoiceItemList = new ArrayList<>();
        invoiceId1InvoiceItemList.add(invoiceItemResponse1);
        invoiceId1InvoiceItemList.add(invoiceItemResponse2);
        invoiceId2InvoiceItemList.add(invoiceItemResponse3);

        // Set up InvoiceItem Lists by InventoryId
        List<InvoiceItem> inventoryId1InvoiceItemList = new ArrayList<>();
        List<InvoiceItem> inventoryId2InvoiceItemList = new ArrayList<>();
        inventoryId1InvoiceItemList.add(invoiceItemResponse1);
        inventoryId2InvoiceItemList.add(invoiceItemResponse2);
        inventoryId2InvoiceItemList.add(invoiceItemResponse3);

        // Mocking addInvoiceItem() DAO method
        Mockito.doReturn(invoiceItemResponse1).when(invoiceItemDao).addInvoiceItem(invoiceItemInput1);
        Mockito.doReturn(invoiceItemResponse2).when(invoiceItemDao).addInvoiceItem(invoiceItemInput2);
        Mockito.doReturn(invoiceItemResponse3).when(invoiceItemDao).addInvoiceItem(invoiceItemInput3);

        // Mocking getInvoiceItem() DAO method
        Mockito.doReturn(invoiceItemResponse1).when(invoiceItemDao).getInvoiceItem(1);
        Mockito.doReturn(invoiceItemResponse2).when(invoiceItemDao).getInvoiceItem(2);
        Mockito.doReturn(invoiceItemResponse3).when(invoiceItemDao).getInvoiceItem(3);

        // Mocking getAllInvoiceItem() DAO method
        Mockito.doReturn(allInvoiceItemList).when(invoiceItemDao).getAllInvoiceItem();

        // Mocking getInvoiceItemByInvoiceId() DAO method
        Mockito.doReturn(invoiceId1InvoiceItemList).when(invoiceItemDao).getInvoiceItemByInvoiceId(1);
        Mockito.doReturn(invoiceId2InvoiceItemList).when(invoiceItemDao).getInvoiceItemByInvoiceId(2);

        // Mocking getInvoiceItemByInventoryId() DAO method
        Mockito.doReturn(inventoryId1InvoiceItemList).when(invoiceItemDao).getInvoiceItemByInventoryId(1);
        Mockito.doReturn(inventoryId2InvoiceItemList).when(invoiceItemDao).getInvoiceItemByInventoryId(2);

        // Mocking updateInvoiceItem() DAO method
        Mockito.doNothing().when(invoiceItemDao).updateInvoiceItem(invoiceItemArgumentCaptor.capture());

        // Mocking deleteInvoiceItem() DAO method
        Mockito.doNothing().when(invoiceItemDao).deleteInvoiceItem(integerArgumentCaptor.capture());
    }
}

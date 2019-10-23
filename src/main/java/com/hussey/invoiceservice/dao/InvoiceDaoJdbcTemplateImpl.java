package com.hussey.invoiceservice.dao;
import com.hussey.invoiceservice.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {
    // Prepared SQL Statements (Constraints)
    private static final String INSERT_INVOICE_SQL =
            "INSERT INTO invoice (customer_id, purchase_date) VALUES (?, ?)";
    private static final String SELECT_INVOICE_SQL =
            "SELECT * FROM invoice WHERE invoice_id = ?";
    private static final String SELECT_ALL_INVOICE_SQL =
            "SELECT * FROM invoice";
    private static final String SELECT_INVOICE_BY_CUSTOMER_ID_SQL =
            "SELECT * FROM invoice WHERE customer_id = ?";
    private static final String UPDATE_INVOICE_SQL =
            "UPDATE invoice SET customer_id = ? WHERE invoice_id = ?";
    private static final String DELETE_INVOICE_SQL =
            "DELETE FROM invoice WHERE invoice_id = ?";

    // Properties
    private JdbcTemplate jdbcTemplate;

    // Constructors
    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Methods
    @Override
    @Transactional
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(INSERT_INVOICE_SQL,
                invoice.getCustomerId(),
                invoice.getPurchaseDate());
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        invoice.setInvoiceId(id);
        return invoice;
    }

    @Override
    public Invoice getInvoice(int invoiceId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapRowToInvoice, invoiceId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return jdbcTemplate.query(SELECT_ALL_INVOICE_SQL, this::mapRowToInvoice);
    }

    @Override
    public List<Invoice> getInvoiceByCustomerId(int customerId) {
        return jdbcTemplate.query(SELECT_INVOICE_BY_CUSTOMER_ID_SQL, this::mapRowToInvoice, customerId);
    }

    @Override
    @Transactional
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL,
                invoice.getCustomerId(),
                invoice.getInvoiceId());
    }

    @Override
    @Transactional
    public void deleteInvoice(int invoiceId) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, invoiceId);
    }

    private Invoice mapRowToInvoice(ResultSet resultSet, int rowNumber) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(resultSet.getInt("invoice_id"));
        invoice.setCustomerId(resultSet.getInt("customer_id"));
        invoice.setPurchaseDate(resultSet.getDate("purchase_date").toLocalDate());
        return invoice;
    }
}

package com.esms.invoice.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import com.esms.invoice.domain.entity.Invoice;
import com.esms.invoice.domain.entity.SaleDetailsDto;
import com.esms.invoice.domain.service.InvoiceService;
import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository implements InvoiceService {
    private Connection connection;

    public InvoiceRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Invoice> findInvoiceById(int id) {
        String sql = """
            SELECT s.id AS sale_id, s.sale_date, s.total_amount,
                   c.name AS customer_name, c.email, c.phone_id,
                   p.name AS product_name, sd.quantity, sd.unit_price,
                   e.first_name, e.last_name,
                   b.name AS branch_name
            FROM sale s
            JOIN sale_details sd ON s.id = sd.sale_id
            JOIN customer c ON s.customer_id = c.id
            JOIN product p ON sd.product_id = p.id
            JOIN employee e ON s.employee_id = e.id
            JOIN branch b ON s.branch_id = b.id
            WHERE s.id = ?;
            """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Invoice invoice = new Invoice(
                        rs.getInt("sale_id"),
                        rs.getDate("sale_date"),
                        rs.getBigDecimal("total_amount"),
                        rs.getString("customer_name"),
                        rs.getString("email"),
                        rs.getInt("phone_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("branch_name"),
                        extractSaleDetails(rs, id)  // Extraemos detalles de la venta
                    );
                    return Optional.of(invoice);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private List<SaleDetailsDto> extractSaleDetails(ResultSet rs, int saleId) throws SQLException {
        List<SaleDetailsDto> saleDetailsList = new ArrayList<>();
        do {
            SaleDetailsDto saleDetail = new SaleDetailsDto(
                rs.getString("product_name"),
                rs.getInt("quantity"),
                rs.getBigDecimal("unit_price")
            );
            saleDetailsList.add(saleDetail);
        } while (rs.next());
        return saleDetailsList;
    }
}


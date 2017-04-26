package com.packt.webstore.domain.repository.impl;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Erling Molde on 02.11.2016.
 */
@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getAllCustomers() {
        return namedParameterJdbcTemplate.query("SELECT * FROM customer", Collections.emptyMap(), new CustomerMapper());
    }

    @Override
    public boolean isCustomerExist(String customerId) {
        Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM CUSTOMER WHERE NAME = ?", Integer.class, customerId);
        return count > 0;
    }

    @Override
    public Customer getCustomer(String customerId) {
        return jdbcTemplate.queryForObject("SELECT * FROM CUSTOMER WHERE NAME = ?", new CustomerMapper(), customerId);
    }

    @Override
    public void saveCustomer(Customer customer) {
        String sql = "INSERT INTO CUSTOMER (ID, NAME, PHONE_NUMBER, BILLING_ADDRESS_ID) VALUES (:id, :name, :phoneNumber, :addressId)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", customer.getCustomerId());
        params.put("name", customer.getName());
        params.put("phoneNumber", customer.getPhoneNumber());
        params.put("addressId", customer.getBillingAddress().getId());
        namedParameterJdbcTemplate.update(sql, params);
    }

    private static class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int i) throws SQLException {
            Customer c = new Customer();
            c.setCustomerId((Long) rs.getObject("ID"));
            c.setName(rs.getString("NAME"));
            c.setPhoneNumber(rs.getString("PHONE_NUMBER"));
            Address address = new Address();
            address.setId(rs.getLong("BILLING_ADDRESS_ID"));
            c.setBillingAddress(address);
            return c;
        }
    }
}
package com.packt.webstore.domain.repository.impl;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Erling Molde on 02.11.2016.
 */
@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query("SELECT * FROM customers", Collections.emptyMap(), new CustomerMapper());
    }

    private static class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int i) throws SQLException {
            Customer c = new Customer();
            c.setCustomerId((Long) rs.getObject("ID"));
            c.setName(rs.getString("NAME"));
            c.setAddress(rs.getString("ADDRESS"));
            c.setNoOfOrdersMade(rs.getLong("NUMBER_OF_ORDERS"));
            return c;
        }
    }
}

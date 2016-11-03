package com.packt.webstore.domain.repository.impl;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
public class InMemoryProductRepository implements ProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getAllProducts() {
        Map<String, Object> params = new HashMap<>();
        return jdbcTemplate.query("SELECT * FROM products", params, new ProductMapper());
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        String sql = "SELECT * FROM PRODUCTS WHERE CATEGORY = :category";
        Map<String, Object> params = Collections.singletonMap("category", category);
        return jdbcTemplate.query(sql, params, new ProductMapper());
    }

    @Override
    public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        String sql = "SELECT * FROM PRODUCTS WHERE CATEGORY IN (:categories) AND MANUFACTURER IN (:brands)";
        return jdbcTemplate.query(sql, filterParams, new ProductMapper());
    }

    @Override
    public Product getProductById(String productId) {
        String sql = "SELECT * FROM PRODUCTS WHERE ID = :id";
        Map<String, Object> params = Collections.singletonMap("id", productId);
        try {
            return jdbcTemplate.queryForObject(sql, params, new ProductMapper());
        } catch (DataAccessException e) {
            throw new ProductNotFoundException(productId);
        }
    }

    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO PRODUCTS (ID, NAME, DESCRIPTION, UNIT_PRICE, MANUFACTURER, CATEGORY, CONDITION, UNITS_IN_STOCK, UNITS_IN_ORDER, DISCONTINUED)" +
                " VALUES (:id, :name, :desc, :price, :manufacturer, :category, :condition, :inStock, :inOrder, :discontinued)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", product.getProductId());
        params.put("name", product.getName());
        params.put("desc", product.getDescription());
        params.put("price", product.getUnitPrice());
        params.put("manufacturer", product.getManufacturer());
        params.put("category", product.getCategory());
        params.put("condition", product.getCondition());
        params.put("inStock", product.getUnitsInStock());
        params.put("inOrder", product.getUnitsInOrder());
        params.put("discontinued", product.isDiscontinued());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updateStock(String productId, long noOfUnits) {
        String sql = "UPDATE PRODUCTS SET UNITS_IN_STOCK = :unitsInStock WHERE ID = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("unitsInStock", noOfUnits);
        params.put("id", productId);

        jdbcTemplate.update(sql, params);
    }

    private static class ProductMapper implements org.springframework.jdbc.core.RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int i) throws SQLException {
            Product product = new Product();
            product.setProductId(rs.getString("ID"));
            product.setName(rs.getString("NAME"));
            product.setDescription(rs.getString("DESCRIPTION"));
            product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
            product.setManufacturer(rs.getString("MANUFACTURER"));
            product.setCategory(rs.getString("CATEGORY"));
            product.setCondition(rs.getString("CONDITION"));
            product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
            product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
            product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
            return product;
        }
    }
}

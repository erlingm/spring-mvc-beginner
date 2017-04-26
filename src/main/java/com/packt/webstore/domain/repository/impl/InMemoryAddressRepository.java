package com.packt.webstore.domain.repository.impl;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Erling Molde on 26.11.2016.
 */
@Repository
public class InMemoryAddressRepository implements AddressRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long saveAddress(Address address) {
        String SQL = "INSERT INTO ADDRESS(DOOR_NO,STREET_NAME,AREA_NAME,STATE,COUNTRY,ZIP) VALUES (:doorNo, :streetName, :areaName, :state, :country, :zip)";

        Map<String, Object> params = new HashMap<>();
        params.put("doorNo", address.getDoorNo());
        params.put("streetName", address.getStreetName());
        params.put("areaName", address.getAreaName());
        params.put("state", address.getState());
        params.put("country", address.getCountry());
        params.put("zip", address.getZipCode());

        SqlParameterSource paramSource = new MapSqlParameterSource(params);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL, paramSource, keyHolder, new String[]{"ID"});
        address.setId(keyHolder.getKey().longValue());
        return address.getId();
    }

    @Override
    public Address getAddress(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ADDRESS WHERE ID = ?", new AddressRowMapper(), id);
    }

    @Override
    public List<Address> getAllAddresses() {
        return jdbcTemplate.query("SELECT * FROM ADDRESS", new AddressRowMapper());
    }

    @Override
    public void delete(Address address) {
        delete(address.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM ADDRESS WHERE ID = ?", id);
    }

    @Override
    public boolean isExistAddress(Long id) {
        Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM ADDRESS WHERE ID = ?", Integer.class, id);
        return count != null && count > 0;
    }

    private static class AddressRowMapper implements RowMapper<Address> {
        @Override
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address();
            address.setId(rs.getLong("ID"));
            address.setDoorNo(rs.getString("DOOR_NO"));
            address.setStreetName(rs.getString("STREET_NAME"));
            address.setAreaName(rs.getString("AREA_NAME"));
            address.setState(rs.getString("STATE"));
            address.setCountry(rs.getString("COUNTRY"));
            address.setZipCode(rs.getString("ZIP"));
            return address;
        }
    }
}
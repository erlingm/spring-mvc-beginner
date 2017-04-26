package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Address;

import java.util.List;

/**
 * Created by Erling Molde on 26.11.2016.
 */
public interface AddressRepository {
    Long saveAddress(Address address);

    Address getAddress(Long id);

    List<Address> getAllAddresses();

    void delete(Address address);

    void delete(Long id);

    boolean isExistAddress(Long id);
}
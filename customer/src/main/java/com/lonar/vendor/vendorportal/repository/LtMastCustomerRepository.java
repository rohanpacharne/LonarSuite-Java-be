package com.lonar.vendor.vendorportal.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.LtMastCustomer;
@Repository
public interface LtMastCustomerRepository extends DataTablesRepository<LtMastCustomer, Long> {

}

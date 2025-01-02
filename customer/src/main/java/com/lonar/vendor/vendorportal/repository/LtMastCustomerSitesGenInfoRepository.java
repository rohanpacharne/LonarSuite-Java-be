package com.lonar.vendor.vendorportal.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.LtMastCustSiteGenInfo;

@Repository
public interface LtMastCustomerSitesGenInfoRepository extends DataTablesRepository<LtMastCustSiteGenInfo, Long> {

}

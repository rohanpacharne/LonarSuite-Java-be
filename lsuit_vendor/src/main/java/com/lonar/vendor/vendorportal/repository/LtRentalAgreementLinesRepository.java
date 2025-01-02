package com.lonar.vendor.vendorportal.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementLines;

public interface LtRentalAgreementLinesRepository extends DataTablesRepository<LtRentalAgreementLines, Long>{

}

package com.lonar.vendor.vendorportal.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.LtPrLines;

public interface LtPrLinesRepository extends DataTablesRepository<LtPrLines, Long>{

}

package com.lonar.vendor.vendorportal.reports;




import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lonar.vendor.vendorportal.model.LtMastReportRequest;

public interface LtMastReportRequestRepository extends DataTablesRepository<LtMastReportRequest, Long>{



}
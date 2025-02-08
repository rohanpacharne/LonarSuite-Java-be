package com.lonar.vendor.vendorportal.repository;
 
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;
 
import com.lonar.vendor.vendorportal.model.SupportRequest;
 
@Repository
public interface SupportRequestRepository extends DataTablesRepository<SupportRequest,Long> {
 
}
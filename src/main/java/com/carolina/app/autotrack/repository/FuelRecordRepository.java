package com.carolina.app.autotrack.repository;

import com.carolina.app.autotrack.model.FuelRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelRecordRepository extends JpaRepository<FuelRecord, Long> {
}

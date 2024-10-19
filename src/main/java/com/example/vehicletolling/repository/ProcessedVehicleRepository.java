package com.example.vehicletolling.repository;

import com.example.vehicletolling.model.ProcessedVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ProcessedVehicleRepository extends JpaRepository<ProcessedVehicle, Long> {
  Optional<ProcessedVehicle> findByVehicleIdAndDate(String vehicleId, LocalDate date);
}


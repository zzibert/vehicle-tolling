package com.example.vehicletolling.repository;

import com.example.vehicletolling.model.VehicleBrandCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface VehicleBrandCountRepository extends JpaRepository<VehicleBrandCount, Long> {
  Optional<VehicleBrandCount> findByVehicleBrandAndDate(String vehicleBrand, LocalDate date);
}


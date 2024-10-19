package com.example.vehicletolling.service;

import com.example.vehicletolling.model.ProcessedVehicle;
import com.example.vehicletolling.model.VehicleBrand;
import com.example.vehicletolling.model.VehicleBrandCount;
import com.example.vehicletolling.repository.ProcessedVehicleRepository;
import com.example.vehicletolling.repository.VehicleBrandCountRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class VehicleProcessingService {

  private final ProcessedVehicleRepository processedVehicleRepository;
  private final VehicleBrandCountRepository vehicleBrandCountRepository;
  private final ObjectMapper objectMapper;

  public VehicleProcessingService(ProcessedVehicleRepository processedVehicleRepository,
                                  VehicleBrandCountRepository vehicleBrandCountRepository,
                                  ObjectMapper objectMapper) {
    this.processedVehicleRepository = processedVehicleRepository;
    this.vehicleBrandCountRepository = vehicleBrandCountRepository;
    this.objectMapper = objectMapper;
  }

  @Transactional
  public void processMessage(String message) throws Exception {
    // Parse JSON message
    JsonNode jsonNode = objectMapper.readTree(message);

    Integer tollStationId = jsonNode.get("tollStationId").asInt();
    String vehicleId = jsonNode.get("vehicleId").asText();
    String vehicleBrandStr = jsonNode.get("vehicleBrand").asText();
    Long timestamp = jsonNode.get("timestamp").asLong();

    VehicleBrand vehicleBrand = VehicleBrand.fromString(vehicleBrandStr);

    // Convert timestamp to LocalDate
    LocalDate date = Instant.ofEpochMilli(timestamp).atZone(ZoneId.of("UTC")).toLocalDate();

    // Check if the vehicle has already been processed for the day
    Optional<ProcessedVehicle> processedOpt = processedVehicleRepository.findByVehicleIdAndDate(vehicleId, date);
    if (processedOpt.isEmpty()) {
      // Mark vehicle as processed
      ProcessedVehicle processedVehicle = new ProcessedVehicle(vehicleId, date);
      processedVehicleRepository.save(processedVehicle);

      // Update vehicle brand count
      Optional<VehicleBrandCount> brandCountOpt = vehicleBrandCountRepository.findByVehicleBrandAndDate(vehicleBrand, date);
      if (brandCountOpt.isPresent()) {
        VehicleBrandCount brandCount = brandCountOpt.get();
        brandCount.setCount(brandCount.getCount() + 1);
        vehicleBrandCountRepository.save(brandCount);
      } else {
        VehicleBrandCount brandCount = new VehicleBrandCount(vehicleBrand, 1L, date);
        vehicleBrandCountRepository.save(brandCount);
      }
    }
  }
}


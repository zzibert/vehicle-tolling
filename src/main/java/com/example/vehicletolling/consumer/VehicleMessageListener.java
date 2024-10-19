package com.example.vehicletolling.consumer;

import com.example.vehicletolling.service.VehicleProcessingService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class VehicleMessageListener {

  private final VehicleProcessingService vehicleProcessingService;

  public VehicleMessageListener(VehicleProcessingService vehicleProcessingService) {
    this.vehicleProcessingService = vehicleProcessingService;
  }

  @KafkaListener(topics = "vehicle-topic", groupId = "vehicle-tolling-group")
  public void listen(String message) {
    try {
      vehicleProcessingService.processMessage(message);
      // Optionally, log successful processing
      System.out.println("Processed message: " + message);
    } catch (Exception e) {
      // Handle exceptions (e.g., log and continue)
      System.err.println("Error processing message: " + message);
      e.printStackTrace();
    }
  }
}


package com.example.vehicletolling.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "processed_vehicle", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"vehicle_id", "date"})
})
public class ProcessedVehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "vehicle_id", nullable = false)
  private String vehicleId;

  @Column(name = "date", nullable = false)
  private LocalDate date;

  // Constructors
  public ProcessedVehicle() {
  }

  public ProcessedVehicle(String vehicleId, LocalDate date) {
    this.vehicleId = vehicleId;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public String getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}


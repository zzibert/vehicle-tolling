package com.example.vehicletolling.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vehicle_brand_count", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"vehicle_brand", "date"})
})
public class VehicleBrandCount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="vehicle_brand", nullable = false)
  private VehicleBrand vehicleBrand;

  @Column(name="count", nullable = false)
  private Long count;

  @Column(name="date", nullable = false)
  private LocalDate date;

  public VehicleBrandCount() {}

  public VehicleBrandCount(VehicleBrand vehicleBrand, Long count, LocalDate date) {
    this.vehicleBrand = vehicleBrand;
    this.count = count;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public VehicleBrand getVehicleBrand() {
    return vehicleBrand;
  }

  public void setVehicleBrand(VehicleBrand vehicleBrand) {
    this.vehicleBrand = vehicleBrand;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}


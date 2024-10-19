package com.example.vehicletolling.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VehicleBrand {
  VOLKSWAGEN,
  BMW,
  NISSAN,
  TOYOTA,
  FORD,
  HONDA,
  BYD,
  TESLA,
  HYUNDAI,
  OTHER;

  @JsonCreator
  public static VehicleBrand fromString(String brand) {
    try {
      return VehicleBrand.valueOf(brand.toUpperCase());
    } catch (Exception e) {
      return OTHER;
    }
  }

  @JsonValue
  public String getBrand() {
    return this.name();
  }
}


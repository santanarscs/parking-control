package com.santanarscs.api.dtos;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CarDto {
  
  @NotBlank
  @Size(max = 7)
  private String licensePlate;

  @NotBlank
  private String brand;

  @NotBlank
  private String model;

  @NotBlank
  private String color;

  // @NotBlank
  private UUID clientId;

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public UUID getClientId() {
    return clientId;
  }

  public void setClientId(UUID clientId) {
    this.clientId = clientId;
  }
  
  
}

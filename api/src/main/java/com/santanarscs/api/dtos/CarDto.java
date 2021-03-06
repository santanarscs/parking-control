package com.santanarscs.api.dtos;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

  @NotNull
  private UUID clientId;

}

package com.santanarscs.api.dtos;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarSpotDto {

  @NotNull
  private UUID carId;
  
  @NotNull
  private UUID spotId;

}

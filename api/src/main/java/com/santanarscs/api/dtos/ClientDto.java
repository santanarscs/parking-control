package com.santanarscs.api.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

  @NotBlank  
  private String name;

  @NotBlank
  @Size(max =11)
  private String document;
  


  
}

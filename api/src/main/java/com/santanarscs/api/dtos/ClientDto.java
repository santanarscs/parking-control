package com.santanarscs.api.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

  @NotBlank(message = "{field.name.required}")  
  private String name;

  @NotBlank(message = "{field.cpf.required}")
  @Size(max =11)
  @CPF(message = "{field.cpf.valid}")
  private String document;
  


  
}

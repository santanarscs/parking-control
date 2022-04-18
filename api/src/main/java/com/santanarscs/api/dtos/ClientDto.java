package com.santanarscs.api.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClientDto {

  @NotBlank  
  private String name;

  @NotBlank
  @Size(max =11)
  private String document;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDocument() {
    return document;
  }
  public void setDocument(String document) {
    this.document = document;
  }

  
}

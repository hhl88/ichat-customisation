package com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DemandInfoItemDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private String name;
 
  @ApiModelProperty(required = true)
  @NotNull
  @JsonProperty
  private boolean required;
  
  
  @ApiModelProperty(required = true)
  @NotNull
  private String example;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty
  public boolean required() {
    return required;
  }

  @JsonProperty
  public void setRequired(boolean required) {
    this.required = required;
  }

  public String getExample() {
    return example;
  }

  public void setExample(String example) {
    this.example = example;
  }

  @Override
  public String toString() {
    return "DemandInfoItemDTO{" +
            "name='" + name + '\'' +
            ", required=" + required +
            ", example='" + example + '\'' +
            '}';
  }
}

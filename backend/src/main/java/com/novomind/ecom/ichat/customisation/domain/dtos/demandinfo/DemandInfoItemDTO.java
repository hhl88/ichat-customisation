package com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo;


import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.NotNull;


public class DemandInfoItemDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private String name;
 
  @ApiModelProperty(required = true)
  @NotNull
  private boolean required;
  
  @ApiModelProperty(required = true)
  @NotNull
  private String example;

    public DemandInfoItemDTO(@NotNull String name, @NotNull boolean required, @NotNull String example) {
        this.name = name;
        this.required = required;
        this.example = example;
    }


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

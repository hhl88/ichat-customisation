package com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DemandInfoItemDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private String name;
 
  @ApiModelProperty(required = true)
  @NotNull
  private boolean isRequired;
  
  
  @ApiModelProperty(required = true)
  @NotNull
  private String example;
}

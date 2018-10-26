package com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo;


import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DemandInfoCreateDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private String name;
 
  @ApiModelProperty(required = true)
  @NotNull
  private boolean isRequired;
  
}

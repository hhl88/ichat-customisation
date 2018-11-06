package com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo;


import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemandInfoCreateDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private List<DemandInfoItemDTO> demandInfoItemsCreateDTO;
 

}

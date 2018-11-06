package com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemandInfoUpdateDTO {

  @ApiModelProperty(required = true)
  @NotNull
  private List<DemandInfoItemDTO> demandInfoItemsDTO;

}

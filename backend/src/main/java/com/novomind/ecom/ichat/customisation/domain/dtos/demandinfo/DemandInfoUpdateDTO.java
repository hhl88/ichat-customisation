package com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemandInfoUpdateDTO {

  @ApiModelProperty(required = true)
  @NotNull
  private List<DemandInfoItemDTO> demandInfoItems;


  public List<DemandInfoItemDTO> getDemandInfoItems() {
    return demandInfoItems;
  }


  @Override
  public String toString() {
    return "DemandInfoUpdateDTO{" +
            "demandInfoItems=" + demandInfoItems +
            '}';
  }
}

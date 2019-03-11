package com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo;


import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;


public class DemandInfoCreateDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private List<DemandInfoItemDTO> demandInfoList;

    public DemandInfoCreateDTO(@NotNull List<DemandInfoItemDTO> demandInfoList) {
        this.demandInfoList = demandInfoList;
    }


    public List<DemandInfoItemDTO> getDemandInfoList() {
        return demandInfoList;
    }

    public void setDemandInfoList(List<DemandInfoItemDTO> demandInfoList) {
        this.demandInfoList = demandInfoList;
    }
}

package com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo;


import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;


public class DemandInfoUpdateDTO {

    @ApiModelProperty(required = true)
    @NotNull
    private List<DemandInfoItemDTO> demandInfoList;


    public DemandInfoUpdateDTO(@NotNull List<DemandInfoItemDTO> demandInfoList) {
        this.demandInfoList = demandInfoList;
    }

    public void setDemandInfoList(List<DemandInfoItemDTO> demandInfoList) {
        this.demandInfoList = demandInfoList;
    }

    @JsonProperty
    public List<DemandInfoItemDTO> getDemandInfoList() {
        return demandInfoList;
    }

    @Override
    public String toString() {
        return "DemandInfoUpdateDTO{" +
                "demandInfoList=" + demandInfoList +
                '}';
    }
}

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
    @JsonProperty
    private List<DemandInfoItemDTO> demandInfoList;


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

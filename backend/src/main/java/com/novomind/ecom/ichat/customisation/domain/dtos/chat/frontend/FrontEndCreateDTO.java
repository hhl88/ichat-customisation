package com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Connection;
import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.cloud.CloudCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerCreateDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontEndCreateDTO {


    @ApiModelProperty(required = true)
    @NotNull
    private String name;

    @ApiModelProperty(required = true)
    @NotNull
    private Connection connectionType;


    @ApiModelProperty
    @JsonProperty(value="iAgentServer")
    private IAgentServerCreateDTO iAgentServer;

    @ApiModelProperty
    @JsonProperty(value="cloud")
    private CloudCreateDTO cloud;

    @ApiModelProperty(required = true)
    @NotNull
    private String urlPath;

    @ApiModelProperty
    @JsonProperty(value="demandInfo")
    DemandInfoCreateDTO demandInfo;


}

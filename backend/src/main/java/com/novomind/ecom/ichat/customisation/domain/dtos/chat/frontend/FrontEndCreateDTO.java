package com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Connection;
import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.cloud.CloudCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerCreateDTO;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;


public class FrontEndCreateDTO {

    @ApiModelProperty(required = true)
    @NotNull
    private String name;

    @ApiModelProperty(required = true)
    @NotNull
    private Connection connectionType;


    @ApiModelProperty
    private IAgentServerCreateDTO iAgentServer;

    @ApiModelProperty
    private CloudCreateDTO cloud;

    @ApiModelProperty(required = true)
    @NotNull
    private String urlPath;

    @ApiModelProperty
    DemandInfoCreateDTO demandInfo;

    private FrontEndCreateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Connection getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(Connection connectionType) {
        this.connectionType = connectionType;
    }

    @JsonProperty(value = "iAgentServer")
    public IAgentServerCreateDTO getIAgentServer() {
        return iAgentServer;
    }

    public void setIAgentServer(IAgentServerCreateDTO iAgentServer) {
        this.iAgentServer = iAgentServer;
    }

    @JsonProperty(value = "cloud")
    public CloudCreateDTO getCloud() {
        return cloud;
    }

    public void setCloud(CloudCreateDTO cloud) {
        this.cloud = cloud;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    @JsonProperty(value = "demandInfo")
    public DemandInfoCreateDTO getDemandInfo() {
        return demandInfo;
    }

    public void setDemandInfo(DemandInfoCreateDTO demandInfo) {
        this.demandInfo = demandInfo;
    }
}

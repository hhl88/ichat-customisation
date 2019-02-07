package com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend;

import com.novomind.ecom.ichat.customisation.domain.datatypes.Connection;
import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoUpdateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.cloud.CloudUpdateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerUpdateDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class FrontEndUpdateDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private String name;

  @ApiModelProperty
  private IAgentServerUpdateDTO iAgentServer;

  @ApiModelProperty
  private CloudUpdateDTO cloud;

  @ApiModelProperty(required = true)
  @NotNull
  private String urlPath;

  @ApiModelProperty(required = true)
  @NotNull
  private Connection connectionType;

  @ApiModelProperty
  @JsonProperty
  DemandInfoUpdateDTO demandInfo;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("iAgentServer")
  public IAgentServerUpdateDTO getIAgentServer() {
    return iAgentServer;
  }

  public void setiAgentServer(IAgentServerUpdateDTO iAgentServer) {
    this.iAgentServer = iAgentServer;
  }

  public CloudUpdateDTO getCloud() {
    return cloud;
  }

  public void setCloud(CloudUpdateDTO cloud) {
    this.cloud = cloud;
  }

  public String getUrlPath() {
    return urlPath;
  }

  public void setUrlPath(String urlPath) {
    this.urlPath = urlPath;
  }

  public Connection getConnectionType() {
    return connectionType;
  }

  public void setConnectionType(Connection connectionType) {
    this.connectionType = connectionType;
  }

  @JsonProperty("demandInfo")
  public DemandInfoUpdateDTO getDemandInfo() {
    return demandInfo;
  }

  public void setDemandInfo(DemandInfoUpdateDTO demandInfo) {
    this.demandInfo = demandInfo;
  }

  @Override
  public String toString() {
    return "FrontEndUpdateDTO{" +
            "name='" + name + '\'' +
            ", iAgentServer=" + iAgentServer +
            ", cloud=" + cloud +
            ", urlPath='" + urlPath + '\'' +
            ", connectionType=" + connectionType +
            ", demandInfo=" + demandInfo +
            '}';
  }
}

package com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend;

import javax.validation.constraints.NotNull;

import com.novomind.ecom.ichat.customisation.domain.datatypes.Connection;

import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.cloud.CloudCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerCreateDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FrontEndCreateDTO {
 
  
  @ApiModelProperty(required = true)
  @NotNull
  private String name;
  
  @ApiModelProperty(required = true)
  @NotNull
  private Connection connectionType;


  @ApiModelProperty(required = false)
  private IAgentServerCreateDTO iAgentServerCreateDTO;

  @ApiModelProperty(required = false)
  private CloudCreateDTO cloudCreateDTO;

  @ApiModelProperty(required = true)
  @NotNull
  private String urlPath;

  @ApiModelProperty(required = false)
  DemandInfoCreateDTO demandInfoCreateDTO;


}

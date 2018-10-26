package com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.novomind.ecom.ichat.customisation.domain.datatypes.Connection;
import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.cloud.CloudCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerCreateDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontEndCreateDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private String userId;
  
  @ApiModelProperty(required = true)
  @NotNull
  private String name;
  
  @ApiModelProperty(required = true)
  @NotNull
  private Connection connectionType;
  
  
  private IAgentServerCreateDTO iAgentServerCreateDTO;
  
  private CloudCreateDTO cloudCreateDTO;
  
  @ApiModelProperty(required = true)
  @NotNull
  private String urlPath;
  
  List<DemandInfoCreateDTO> demandInfoCreateDTO;
  
}

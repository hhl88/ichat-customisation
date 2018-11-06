package com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend;

import javax.validation.constraints.NotNull;

import com.novomind.ecom.ichat.customisation.domain.datatypes.Connection;

import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoUpdateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.cloud.CloudUpdateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerUpdateDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FrontEndUpdateDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private String name;

  @ApiModelProperty(required = false)
  private IAgentServerUpdateDTO iAgentServerUpdateDTO;

  @ApiModelProperty(required = false)
  private CloudUpdateDTO cloudUpdateDTO;

  @ApiModelProperty(required = true)
  @NotNull
  private String urlPath;

  @ApiModelProperty(required = true)
  @NotNull
  private Connection connectionType;

  @ApiModelProperty(required = false)
  DemandInfoUpdateDTO demandInfoUpdateDTO;

  
}

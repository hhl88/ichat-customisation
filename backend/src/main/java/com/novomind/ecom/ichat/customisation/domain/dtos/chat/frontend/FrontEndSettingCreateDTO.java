package com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.cloud.CloudCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerCreateDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FrontEndSettingCreateDTO {

  @ApiModelProperty(required = true)
  @NotNull
  private FrontEndCreateDTO frontEndCreateDTO;

  @ApiModelProperty(required = false)
  private IAgentServerCreateDTO iAgentServerCreateDTO;

  @ApiModelProperty(required = false)
  private CloudCreateDTO cloudCreateDTO;

  @ApiModelProperty(required = true)
  @NotNull
  private String urlPath;

  @ApiModelProperty(required = false)
  List<DemandInfoCreateDTO> demandInfoCreateDTO;

}

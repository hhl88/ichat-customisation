package com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.cloud.CloudUpdateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerUpdateDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FrontEndSettingUpdateDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private FrontEndUpdateDTO frontEndUpdateDTO;

  @ApiModelProperty(required = false)
  private IAgentServerUpdateDTO iAgentServerUpdateDTO;

  @ApiModelProperty(required = false)
  private CloudUpdateDTO cloudUpdateDTO;

  @ApiModelProperty(required = true)
  @NotNull
  private String urlPath;

  @ApiModelProperty(required = false)
  List<DemandInfoCreateDTO> demandInfoUpdateDTO;

}

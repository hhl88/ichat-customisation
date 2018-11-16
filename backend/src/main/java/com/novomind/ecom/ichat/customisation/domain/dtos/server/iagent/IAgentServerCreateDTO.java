package com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class IAgentServerCreateDTO {
   
  @ApiModelProperty(required = true)
  @NotNull
  private String address;
  
  @ApiModelProperty(required = true)
  @NotNull
  private String userAPI;

  @ApiModelProperty(required = true)
  @NotNull
  private String password;

  @ApiModelProperty(required = true)
  @NotNull
  private String clientId;

  @ApiModelProperty(required = true)
  @NotNull
  private String secret;

}

package com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IAgentServerUpdateDTO {
   
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

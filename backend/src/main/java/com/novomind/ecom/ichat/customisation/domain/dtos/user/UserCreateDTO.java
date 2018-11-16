package com.novomind.ecom.ichat.customisation.domain.dtos.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserCreateDTO {

  @ApiModelProperty
  @NotNull
  String email;
  
}

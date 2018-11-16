package com.novomind.ecom.ichat.customisation.domain.dtos.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserPasswordUpdateDTO {

  @ApiModelProperty
  @NotNull
  private String oldPassword;

  @ApiModelProperty
  @NotNull
  private String newPassword;

  @ApiModelProperty
  @NotNull
  private String retypedPassword;
}

package com.novomind.ecom.ichat.customisation.domain.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserPasswordUpdateDTO {

  @ApiModelProperty
  @NotNull
  @JsonProperty("oldPassword")
  private String oldPassword;

  @ApiModelProperty
  @NotNull
  @JsonProperty("newPassword")
  private String newPassword;

  @ApiModelProperty
  @NotNull
  @JsonProperty("retypedPassword")
  private String retypedPassword;
}

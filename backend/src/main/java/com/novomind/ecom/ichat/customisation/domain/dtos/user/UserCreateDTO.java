package com.novomind.ecom.ichat.customisation.domain.dtos.user;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;


public class UserCreateDTO {

  @ApiModelProperty
  @NotNull
  private String email;

    public UserCreateDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

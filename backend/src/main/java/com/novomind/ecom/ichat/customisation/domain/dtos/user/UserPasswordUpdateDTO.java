package com.novomind.ecom.ichat.customisation.domain.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;


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

    public UserPasswordUpdateDTO(@NotNull String oldPassword, @NotNull String newPassword, @NotNull String retypedPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.retypedPassword = retypedPassword;
    }

    @JsonProperty("oldPassword")
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @JsonProperty("newPassword")
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @JsonProperty("retypedPassword")
    public String getRetypedPassword() {
        return retypedPassword;
    }

    public void setRetypedPassword(String retypedPassword) {
        this.retypedPassword = retypedPassword;
    }
}

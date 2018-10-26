package com.novomind.ecom.ichat.customisation.domain.dtos.user;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPasswordUpdateDTO {
  
  @NotNull
  private String oldPassword;
  
  @NotNull
  private String newPassword;
  
  @NotNull
  private String retypedPassword;
}

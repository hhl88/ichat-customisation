package com.novomind.ecom.ichat.customisation.domain.dtos.user;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDTO {
  
  @NotNull
  String email;
  
}

package com.novomind.ecom.ichat.customisation.core.users;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novomind.ecom.ichat.customisation.domain.dtos.user.UserCreateDTO;

import lombok.Builder;
import lombok.NonNull;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@SuppressWarnings("serial")
@Builder
@NoArgsConstructor
public class IChatUser implements Serializable {
  
  private String id;
  
  @NonNull
  private String email;
  
//  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @JsonIgnore
  private String password;
  
  private Set<Role> roles;

  public static IChatUser of(UserCreateDTO userCreateDTO) {
    return IChatUser.builder().email(userCreateDTO.getEmail()).build();
  }

}

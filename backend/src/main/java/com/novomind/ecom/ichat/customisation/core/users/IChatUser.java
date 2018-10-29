package com.novomind.ecom.ichat.customisation.core.users;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novomind.ecom.ichat.customisation.domain.dtos.user.UserCreateDTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

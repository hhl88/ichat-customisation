package com.novomind.ecom.ichat.customisation.core.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novomind.ecom.ichat.customisation.domain.dtos.user.UserCreateDTO;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Set;

public class IChatUser implements Serializable {
  
  private String id;
  
  @NonNull
  private String email;
  
  @JsonIgnore
  private String password;
  
  private Set<Role> roles;

  private IChatUser() {
  }

  public IChatUser(String id, String email, String password, Set<Role> roles) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public IChatUser(String id, String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public IChatUser setId(String id) {
    this.id = id;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public IChatUser setEmail(String email) {
    this.email = email;
    return this;
  }

  @JsonIgnore
  public String getPassword() {
    return password;
  }

  public IChatUser setPassword(String password) {
    this.password = password;
    return this;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public IChatUser setRoles(Set<Role> roles) {
    this.roles = roles;
    return this;

  }

  public static IChatUser of(UserCreateDTO userCreateDTO) {
    return new IChatUser().setEmail(userCreateDTO.getEmail());
  }

}

package com.novomind.ecom.ichat.customisation.core.users;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {
  public static final String ROLE_USER = "user";
  public static final String ROLE_ADMIN = "admin";
  private long id;

  private String roleName;

  public Role() {
  }

  public Role(String roleName) {
      this.roleName = roleName;
  }

  public long getId() {
      return id;
  }

  public String getRoleName() {
      return roleName;
  }
  
  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Role role = (Role) o;
      return id == role.id &&
              Objects.equals(roleName, role.roleName);
  }

  @Override
  public int hashCode() {

      return Objects.hash(id, roleName);
  }

}

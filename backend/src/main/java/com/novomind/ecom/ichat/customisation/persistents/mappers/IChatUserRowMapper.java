package com.novomind.ecom.ichat.customisation.persistents.mappers;

import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;


public class IChatUserRowMapper implements RowMapper<IChatUser> {

  @Nullable
  @Override
  public IChatUser mapRow(ResultSet rs, int rowNum) throws SQLException {
    return IChatUser
            .builder()
            .id(rs.getString("id"))
            .email(rs.getString("email"))
            .password(rs.getString("password"))
            .build();
  }

}

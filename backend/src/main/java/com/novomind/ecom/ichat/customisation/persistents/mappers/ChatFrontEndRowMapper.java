package com.novomind.ecom.ichat.customisation.persistents.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;

public class ChatFrontEndRowMapper implements RowMapper<ChatFrontEnd> {
  
  @Nullable
  @Override
  public ChatFrontEnd mapRow(ResultSet rs, int rowNum) throws SQLException {
    ChatFrontEnd chatFrontend = new ChatFrontEnd();
    chatFrontend.setId(String.valueOf(rs.getLong("id")));
    chatFrontend.setName(rs.getString("name"));
    chatFrontend.setConnectionType(rs.getString("connection_type"));
    return chatFrontend;
  }

}

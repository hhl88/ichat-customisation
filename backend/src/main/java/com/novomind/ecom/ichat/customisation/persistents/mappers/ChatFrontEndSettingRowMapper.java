package com.novomind.ecom.ichat.customisation.persistents.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.novomind.ecom.ichat.customisation.core.chat.setting.ChatFrontEndSetting;

public class ChatFrontEndSettingRowMapper implements RowMapper<ChatFrontEndSetting> {

  @Nullable
  @Override
  public ChatFrontEndSetting mapRow(ResultSet rs, int rowNum) throws SQLException {
    return ChatFrontEndSetting.builder()
        .iAgentServerId(rs.getString("iagent_server_id"))
        .cloudId(rs.getString("cloud_id"))
        .urlPath(rs.getString("url_path"))
        .build();
  }

}

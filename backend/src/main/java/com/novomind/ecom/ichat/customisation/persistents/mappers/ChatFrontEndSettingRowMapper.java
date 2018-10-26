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
    ChatFrontEndSetting setting = new ChatFrontEndSetting();
    setting.setIAgentServerId(rs.getString("iagent_server_id"));
    setting.setCloudId(rs.getString("cloud_id"));
    setting.setUrlPath(rs.getString("url_path"));
    return setting;
  }

}

package com.novomind.ecom.ichat.customisation.persistents.mappers;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatFrontEndRowMapper implements RowMapper<ChatFrontEnd> {
    @Nullable
    @Override
    public ChatFrontEnd mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ChatFrontEnd(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("user_id"),
                rs.getString("iagent_server_id"),
                rs.getString("cloud_id"),
                rs.getString("url_path"),
                rs.getString("demand_info_id"),
                rs.getInt("connection_type"));
    }

}

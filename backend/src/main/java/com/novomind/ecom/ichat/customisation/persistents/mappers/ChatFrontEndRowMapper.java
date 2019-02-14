package com.novomind.ecom.ichat.customisation.persistents.mappers;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Connection;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatFrontEndRowMapper implements RowMapper<ChatFrontEnd> {
    @Nullable
    @Override
    public ChatFrontEnd mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ChatFrontEnd.builder()
                .id(rs.getString("id"))
                .name(rs.getString("name"))
                .userId(rs.getString("user_id"))
                .iAgentServerId(rs.getString("iagent_server_id"))
                .cloudId(rs.getString("cloud_id"))
                .urlPath(rs.getString("url_path"))
                .demandInfoId(rs.getString("demand_info_id"))
                .connectionType(rs.getInt("connection_type"))
                .build();
    }

}

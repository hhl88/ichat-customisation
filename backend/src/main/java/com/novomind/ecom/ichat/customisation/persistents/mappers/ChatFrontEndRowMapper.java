package com.novomind.ecom.ichat.customisation.persistents.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Connection;

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
                .connectionType(Connection.valueOf(rs.getString("connection_type")))
                .build();
    }

}

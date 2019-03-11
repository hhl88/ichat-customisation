package com.novomind.ecom.ichat.customisation.persistents.mappers;

import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;


public class IAgentServerRowMapper implements RowMapper<IAgentServer> {

    @Nullable
    @Override
    public IAgentServer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new IAgentServer(
                rs.getString("id"),
                rs.getString("address"),
                rs.getString("user_api"),
                rs.getString("password"),
                rs.getString("client_id"),
                rs.getString("secret"));
    }

}

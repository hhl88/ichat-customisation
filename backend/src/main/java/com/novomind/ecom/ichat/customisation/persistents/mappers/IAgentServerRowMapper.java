package com.novomind.ecom.ichat.customisation.persistents.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;


public class IAgentServerRowMapper implements RowMapper<IAgentServer> {

    @Nullable
    @Override
    public IAgentServer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return IAgentServer.builder()
                .id(rs.getString("id"))
                .address(rs.getString("address"))
                .userAPI(rs.getString("user_api"))
                .clientId(rs.getString("client_id"))
                .secret(rs.getString("secret"))
                .build();
    }

}

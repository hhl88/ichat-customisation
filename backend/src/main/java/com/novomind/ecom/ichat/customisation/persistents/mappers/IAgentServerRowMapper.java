package com.novomind.ecom.ichat.customisation.persistents.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;


public class IAgentServerRowMapper implements RowMapper<IAgentServer> {

  @Nullable
  @Override
  public IAgentServer mapRow(ResultSet rs, int rowNum) throws SQLException {
    IAgentServer server = new IAgentServer();
    server.setId(String.valueOf(rs.getLong("id")));
    server.setAddress(rs.getString("address"));
    server.setUserAPI(rs.getString("user_api"));
    server.setClientId(rs.getString("client_id"));
    server.setSecret(rs.getString("secret"));
    return server;
  }

}

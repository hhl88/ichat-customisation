package com.novomind.ecom.ichat.customisation.persistents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import com.novomind.ecom.ichat.customisation.core.interfaces.dao.IAgentServerDao;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.persistents.mappers.IAgentServerRowMapper;

@Repository
public class IAgentServerDaoImpl extends BaseDao implements IAgentServerDao {
  Logger log = LoggerFactory.getLogger(IAgentServerDao.class);

  @Override
  public String insertIAgentServer(IAgentServer server) {
    String query = "INSERT INTO " + " iagent_server(address, user_api, password, client_id, secret) "
        + " VALUES(?, ?, ?, ?, ?)";
    try {
      return String.valueOf(getJdbcTemplate().update(query, server.getAddress(), server.getUserAPI(),
          server.getPassword(), server.getClientId(), server.getSecret()));
    } catch (Exception e) {
      log.error("Cannot insert new agent server");
    }
    return null;
  }

  @Override
  public void updateIAgentServer(String id, IAgentServer server) {
    String query = "UPDATE iagent_server SET " + " address = ?, user_api = ?, password = ?, "
        + " client_id = ?, secret = ?" + " WHERE id = ?";
    try {
      getJdbcTemplate().update(query, server.getAddress(), server.getUserAPI(), server.getPassword(),
          server.getClientId(), server.getSecret(), id);
    } catch (Exception e) {
      log.error("Cannot update agent server");
    }
  }

  @Override
  public IAgentServer findIAgentServerById(String id) {
    String query = "Select * FROM iagent_server WHERE id = ?";
    return DataAccessUtils.singleResult(getJdbcTemplate().query(query, new IAgentServerRowMapper(), id));
  }

  @Override
  public String findIdByInfo(IAgentServer server) {
    String query = "Select id FROM iagent_server " + " WHERE " + "       address LIKE ? AND "
        + "       user_api LIKE ? AND " + "       client_id LIKE ? AND " + "       secret LIKE ?";
    return getJdbcTemplate().queryForObject(query,
        new Object[] { server.getAddress(), server.getUserAPI(), server.getClientId(), server.getSecret() },
        String.class);
  }

}

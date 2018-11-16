package com.novomind.ecom.ichat.customisation.persistents.server.iagent;

import com.novomind.ecom.ichat.customisation.core.interfaces.dao.IAgentServerDao;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.persistents.base.BaseDao;
import com.novomind.ecom.ichat.customisation.persistents.mappers.IAgentServerRowMapper;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.novomind.ecom.ichat.customisation.constants.Constants.IAGENT_SERVER_TABLE;

@Repository
public class IAgentServerDaoImpl extends BaseDao implements IAgentServerDao {

    @Override
    public String insertIAgentServer(IAgentServer server) {
        String generatedId = generateStringIdForTable(IAGENT_SERVER_TABLE, 10);

        String query = "INSERT INTO " + IAGENT_SERVER_TABLE +
                " (id, address, user_api, password, client_id, secret) " +
                " VALUES(?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(query, generatedId, server.getAddress(), server.getUserAPI(),
                server.getPassword(), server.getClientId(), server.getSecret());
        return generatedId;
    }

    @Override
    public void updateIAgentServer(IAgentServer server) {
        String query = "UPDATE " + IAGENT_SERVER_TABLE + " SET " +
                " address = ?, user_api = ?, password = ?, " +
                " client_id = ?, secret = ?" + " WHERE id = ?";
        getJdbcTemplate().update(query, server.getAddress(), server.getUserAPI(), server.getPassword(),
                server.getClientId(), server.getSecret(), server.getId());

    }

    @Override
    public Optional<IAgentServer> findIAgentServerById(String id) {
        String query = "Select * FROM iagent_server WHERE id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(getJdbcTemplate().query(query, new IAgentServerRowMapper(), id)));
    }

}

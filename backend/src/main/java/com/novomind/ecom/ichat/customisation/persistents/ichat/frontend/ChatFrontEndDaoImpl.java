package com.novomind.ecom.ichat.customisation.persistents.ichat.frontend;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatFrontEndDao;
import com.novomind.ecom.ichat.customisation.persistents.base.BaseDao;
import com.novomind.ecom.ichat.customisation.persistents.mappers.ChatFrontEndRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.novomind.ecom.ichat.customisation.constants.Constants.ICHAT_UI_TABLE;

@Repository
public class ChatFrontEndDaoImpl extends BaseDao implements ChatFrontEndDao {
    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String insertChatFrontEnd(ChatFrontEnd chatFrontEnd) {
        String generatedId = generateStringIdForTable(ICHAT_UI_TABLE, 10);
        log.info("chat frontend " + chatFrontEnd);

        String query = "INSERT INTO " +
                ICHAT_UI_TABLE +
                "           (id, user_id, name, iagent_server_id, " +
                "           cloud_id, url_path, demand_info_id,connection_type " +
                "           ) " +
                " VALUES " +
                "       (" +
                "         ?, ?, ?, ?," +
                "         ?, ?, ?, ?" +
                "       )";
        logger.info(chatFrontEnd);
        getJdbcTemplate().update(query,
                generatedId, chatFrontEnd.getUserId(), chatFrontEnd.getName(), chatFrontEnd.getIAgentServerId(),
                chatFrontEnd.getCloudId(), chatFrontEnd.getUrlPath(), chatFrontEnd.getDemandInfoId(),
                chatFrontEnd.getConnectionType().toString());
        return generatedId;

    }

    @Override
    public void updateChatFrontEnd(ChatFrontEnd chatFrontEnd) {
        log.info("update " + chatFrontEnd);
        String query = "UPDATE " + ICHAT_UI_TABLE +
                "       SET name = ?, url_path = ?,  connection_type = ?, " +
                "           cloud_id = ?, iagent_server_id = ?, demand_info_id = ?" +
                "       WHERE id = ?";
        getJdbcTemplate().update(query,
                chatFrontEnd.getName(), chatFrontEnd.getUrlPath(), chatFrontEnd.getConnectionType().toString(),
                chatFrontEnd.getCloudId(), chatFrontEnd.getIAgentServerId(), chatFrontEnd.getDemandInfoId(),
                chatFrontEnd.getId());
    }

    @Override
    public Optional<ChatFrontEnd> findChatFrontEndById(String id) {
        String query = "SELECT * FROM " + ICHAT_UI_TABLE + " WHERE id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(getJdbcTemplate().query(query, new ChatFrontEndRowMapper(), id)));
    }

    @Override
    public List<ChatFrontEnd> findChatFrontEndByUserId(String userId) {
        String query = "SELECT * FROM " + ICHAT_UI_TABLE + " WHERE user_id = ?";
        return getJdbcTemplate().query(query, new ChatFrontEndRowMapper(), userId);
    }


}

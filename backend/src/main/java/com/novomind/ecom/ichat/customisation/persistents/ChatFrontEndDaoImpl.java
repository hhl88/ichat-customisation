package com.novomind.ecom.ichat.customisation.persistents;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatFrontEndDao;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.persistents.mappers.ChatFrontEndRowMapper;

@Repository
public class ChatFrontEndDaoImpl extends BaseDao implements ChatFrontEndDao {

  Logger log = LoggerFactory.getLogger(ChatFrontEndDao.class);

  @Override
  public String insertChatFrontEnd(IChatUser user, ChatFrontEnd chatFrontEnd) {
    String query = "INSERT INTO ichat_ui(user_id, name, connection_type) VALUES(?, ?, ?)";
    try {
      long id = getJdbcTemplate().update(query, user.getId(), chatFrontEnd.getName(), chatFrontEnd.getConnectionType());
      return String.valueOf(id);
    } catch (Exception e) {
      log.error("Cannot add new chat_font_end");
    }
    return null;
  }

  @Override
  public void updateChatFrontEnd(ChatFrontEnd chatFrontEnd) {
    String query = "UPDATE ichat_ui " + " SET name = ?, connection_type = ? " + " WHERE id = ?";
    getJdbcTemplate().update(query, chatFrontEnd.getName(), chatFrontEnd.getConnectionType(), chatFrontEnd.getId());
  }

  @Override
  public ChatFrontEnd findChatFrontEndById(String id) {
    String query = "SELECT * FROM ichat_ui WHERE id = ?";
    return DataAccessUtils.singleResult(getJdbcTemplate().query(query, new ChatFrontEndRowMapper(), id));
  }

  @Override
  public List<ChatFrontEnd> findChatFrontEndByUserId(String userId) {
    String query = "SELECT * FROM ichat_ui WHERE user_id = ?";
    return getJdbcTemplate().query(query, new ChatFrontEndRowMapper(), userId);  }

}

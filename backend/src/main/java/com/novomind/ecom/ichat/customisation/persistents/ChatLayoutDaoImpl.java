package com.novomind.ecom.ichat.customisation.persistents;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.common.FontStyleConverter;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatLayoutDao;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.persistents.mappers.ChatLayoutRowMapper;

@Repository
public class ChatLayoutDaoImpl extends BaseDao implements ChatLayoutDao{
  
  Logger log = LoggerFactory.getLogger(ChatLayoutDao.class);
  
  @Override
  public String insertChatLayout(IChatUser user, ChatLayout chatLayout) {
    String query = "INSERT INTO chat_layout ( "
        + " user_id, name, display_type, text_input_type, "
        + " buton_type, logo, background_img, background_type, "
        + " font_family, font_size, font_styles) "
        + " VALUES ( "
        + " ?, ?, ?, ?, "
        + " ?, ?, ?, ?, "
        + " ?, ?, ?)";
    try {
      long id = getJdbcTemplate()
          .update(query, 
                      user.getId(), chatLayout.getName(), chatLayout.getDisplayType(), chatLayout.getTextInputType(), 
                      chatLayout.getButtonType(), chatLayout.getLogo(), chatLayout.getBackgroundImg(), chatLayout.getBackgroundType(),
                      chatLayout.getFontFamily(), chatLayout.getFontSize(), Integer.valueOf(FontStyleConverter.fontStylesToBit(chatLayout.getFontStyles())));
      return String.valueOf(id);
    } catch (Exception e) {
      logger.error("Cannot add new Chat Layout");
    }
    return null;
  }

  @Override
  public void updateChatLayout(ChatLayout chatLayout) {
    String query = "UPDATE chat_layout SET "
        + " name = ?,  display_type = ?, text_input_type = ?, buton_type = ?, " 
        + " logo = ?, background_img = ?, background_type = ?, " 
        + " font_family = ?, font_size = ?, font_styles = ? "
        + " WHERE id = ?";
    getJdbcTemplate() .update(query,
        chatLayout.getName(), chatLayout.getDisplayType(), chatLayout.getTextInputType(), chatLayout.getButtonType(),
        chatLayout.getLogo(), chatLayout.getBackgroundImg(), chatLayout.getBackgroundType(),
        chatLayout.getFontFamily(), chatLayout.getFontSize(), Integer.valueOf(FontStyleConverter.fontStylesToBit(chatLayout.getFontStyles())),
        chatLayout.getId());
  }

  @Override
  public ChatLayout findChatLayoutById(String id) {
    String query = "Select * FROM chat_layout WHERE id = ?";
    return DataAccessUtils.singleResult(getJdbcTemplate().query(query, new ChatLayoutRowMapper(), id));
  }

  @Override
  public List<ChatLayout> findChatLayoutByUserId(String userId) {
    String query = "Select * FROM chat_layout WHERE user_id = ?";
    return getJdbcTemplate().query(query, new ChatLayoutRowMapper(), userId);
  }

}

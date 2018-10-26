package com.novomind.ecom.ichat.customisation.persistents;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.novomind.ecom.ichat.customisation.constants.Constants;
import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChooseChatFrontEndDao;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

@Repository
public class ChooseChatFrontEndDaoImpl extends BaseDao implements ChooseChatFrontEndDao {

  Logger log = LoggerFactory.getLogger(ChooseChatFrontEndDao.class);

//  @Override
//  public String insertNewCustom(ChatFrontEndCustom custom) {
//    String query = "INSERT INTO " + Constants.ICHAT_UI_CUSTOM_TABLE +"(user_id, c_ui_setting_id, demand_info_id) "
//        + " VALUES(?, ?, ?)";
//    try {
//      getJdbcTemplate().update(query, custom.getUserId(), custom.getSettingId(), custom.getDemandInfoId());
//    } catch (Exception e ) {
//      log.error("Cannot inser new custom");
//    }
//    return custom.getUserId();
//  }
//
//  @Override
//  public void updateCustom(ChatFrontEndCustom custom) {
//    String query = "UPDATE "  + Constants.ICHAT_UI_CUSTOM_TABLE 
//        + " SET c_ui_setting_id = ? AND demand_info_id = ? "
//        + " WHERE user_id = ?";
//    getJdbcTemplate().update(query, custom.getSettingId(), custom.getDemandInfoId(), custom.getUserId());
//  }
//
//  @Override
//  public List<ChatFrontEndCustom> findChatFrontEndCustomByUserId(String userId) {
//    String query = "SELECT * FROM " + Constants.ICHAT_UI_CUSTOM_TABLE +" WHERE user_id = ?";
//    return getJdbcTemplate().query(query, new ChatFrontEndCustomRowMapper(), userId);
//  }

//  @Override
//  public void insert(IChatUser user, ConnectionType type, String chatFrontEndId, String serverId, String urlPath,
//      String demandInfoId) {
//    String server = type == ConnectionType.IAGENT_SERVER ? "iagent_server_id" : "cloud_id";
//    String query = "INSERT INTO " + Constants.CHOOSE_CHAT_UI_TABLE +"(c_ui_setting_id, demand_info_id, url_path, " + server + ") "
//        + " VALUES(?, ?, ?)";
//    try {
//      getJdbcTemplate().update(query, chatFrontEndId, demandInfoId, urlPath, serverId);
//    } catch (Exception e ) {
//      log.error("Cannot inser new custom");
//    }            
//  }
//
//  @Override
//  public void changeToAnother(IChatUser user, ConnectionType type, String chatFrontEndId, String serverId,
//      String urlPath, String demandInfoId) {
//    // TODO Auto-generated method stub
//    
//  }

  @Override
  public void insert(IChatUser user, ChatFrontEnd chatFrontEnd) {
    String query = "INSERT INTO " + Constants.CHOOSE_CHAT_UI_TABLE + "(user_id, ichat_ui_id)" + " VALUES(?, ?)";
    try {
      getJdbcTemplate().update(query, user.getId(), chatFrontEnd.getId());
    } catch (Exception e) {
      log.error("user cannot insert new chat front end");
    }

  }

  @Override
  public void changeToAnother(IChatUser user, ChatFrontEnd chatFrontEnd) {
    String query = "UPDATE " + Constants.CHOOSE_CHAT_UI_TABLE + " SET  ichat_ui_id = ? WHERE user_id = ?";
    try {
      getJdbcTemplate().update(query, chatFrontEnd.getId(), user.getId());
    } catch (Exception e) {
      log.error("Cannot choose another chat front end");
    }

  }

}

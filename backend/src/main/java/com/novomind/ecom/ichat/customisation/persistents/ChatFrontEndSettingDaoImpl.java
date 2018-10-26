package com.novomind.ecom.ichat.customisation.persistents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import com.novomind.ecom.ichat.customisation.constants.Constants;
import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.chat.setting.ChatFrontEndSetting;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatFrontEndSettingDao;
import com.novomind.ecom.ichat.customisation.persistents.mappers.ChatFrontEndSettingRowMapper;

@Repository
public class ChatFrontEndSettingDaoImpl extends BaseDao implements ChatFrontEndSettingDao {

  Logger log = LoggerFactory.getLogger(ChatFrontEndSettingDao.class);

  @Override
  public String insertNewSetting(ChatFrontEnd chatFrontEnd, ChatFrontEndSetting setting) {
    String query = "INSERT INTO ichat_ui_setting(ichat_ui_id, iagent_server_id, cloud_id, url_path, demand_info_id) "
        + " VALUES(?, ?, ?, ?, ?)";
    try {
      long id = getJdbcTemplate().update(query, chatFrontEnd.getId(), setting.getIAgentServerId(), setting.getCloudId(),
          setting.getUrlPath(), setting.getDemandInfoId());
      return String.valueOf(id);
    } catch (Exception e) {
      log.error("Cannot insert new chat_front_end_setting");
    }
    return null;
  }

  @Override
  public void updateSetting(ChatFrontEnd chatFrontEnd, ChatFrontEndSetting setting) {
    String query = "UPDATE ichat_ui_setting "
        + " SET AND iagent_server_id = ? AND cloud_id = ? AND url_path = ? AND demand_info_id = ? "
        + " WHERE ichat_ui_id = ? ";
    try {
      getJdbcTemplate().update(query, setting.getIAgentServerId(), setting.getCloudId(), setting.getUrlPath(),
          setting.getDemandInfoId(), chatFrontEnd.getId());
    } catch (Exception e) {
      log.error("Cannot update chat_front_end_setting");
    }
  }

  @Override
  public ChatFrontEndSetting findSettingByInfoChatFrontEndId(String chatFrontEndId) {
    String query = "SELECT * FROM " + Constants.ICHAT_UI_SETTING_TABLE + " WHERE ichat_ui_id = ?";
    return DataAccessUtils
        .singleResult(getJdbcTemplate().query(query, new ChatFrontEndSettingRowMapper(), chatFrontEndId));
  }

}

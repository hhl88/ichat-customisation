package com.novomind.ecom.ichat.customisation.core.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.chat.setting.ChatFrontEndSetting;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatFrontEndDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatFrontEndSettingDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.DemandInfoDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.IAgentServerDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatFrontEndSettingService;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;

@Service
public class ChatFrontEndSettingServiceImpl implements ChatFrontEndSettingService {

  @Autowired
  IAgentServerDao serverDao;

  @Autowired
  ChatFrontEndDao chatFrontEndDao;
  
  @Autowired
  DemandInfoDao demandInfoDao;

  @Autowired
  ChatFrontEndSettingDao settingDao;

  @Override
  public ChatFrontEndSetting findSettingByChatFrontEndyId(String chatFrontEndId) {
    return settingDao.findSettingByInfoChatFrontEndId(chatFrontEndId);
  }

//  @Override
//  public String addNewSetting(IAgentServer server, ChatFrontEnd chatFrontEnd, String urlPath) {
//    String serverId = serverDao.insertIAgentServer(server);
//    String chatFrontEndId = chatFrontEndDao.insertChatFrontEnd(chatFrontEnd);
//    ChatFrontEndSetting setting = new ChatFrontEndSetting();
//    setting.setChatFrontEndId(chatFrontEndId);
//    setting.setIAgentServerId(serverId);
//    setting.setUrlPath(urlPath);
//
//    return settingDao.insertNewSetting(setting);
//  }
//
//  @Override
//  public String addNewSetting(String cloud, ChatFrontEnd chatFrontEnd, String urlPath) {
//    // TODO Auto-generated method stub
//    return null;
//  }

  @Override
  public void updateSetting(ChatFrontEndSetting setting, IAgentServer server, ChatFrontEnd chatFrontEnd,
      String urlPath) {

    String newServerId = serverDao.insertIAgentServer(server);

    chatFrontEndDao.updateChatFrontEnd(chatFrontEnd);

    String oldNewServerId = setting.getIAgentServerId();

    if (newServerId.compareTo(oldNewServerId) != 0) {

    }

  }

  @Override
  public String addNewSetting(ChatFrontEnd chatFrontEnd, IAgentServer server, String urlPath,
      List<DemandInfo> demandInfoList) {
    String serverId = serverDao.insertIAgentServer(server);
    ChatFrontEndSetting setting = new ChatFrontEndSetting();
    setting.setIAgentServerId(serverId);
    setting.setUrlPath(urlPath);
    String demandInfoId = demandInfoDao.insertDemandInfoList(demandInfoList);
    setting.setDemandInfoId(demandInfoId);

    return settingDao.insertNewSetting(chatFrontEnd, setting);
  }

}

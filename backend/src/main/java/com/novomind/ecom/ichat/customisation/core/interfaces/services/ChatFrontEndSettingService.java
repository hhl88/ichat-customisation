package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import java.util.List;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.chat.setting.ChatFrontEndSetting;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;

public interface ChatFrontEndSettingService {
  
  String addNewSetting(ChatFrontEnd chatFrontEnd, IAgentServer server, String urlPath, List<DemandInfo> demandInfoList);
  
  void updateSetting(ChatFrontEndSetting setting, IAgentServer server, ChatFrontEnd chatFrontEnd, String urlPath);

  ChatFrontEndSetting findSettingByChatFrontEndyId(String chatFrontEndId);

}

package com.novomind.ecom.ichat.customisation.core.interfaces.services;


import java.util.List;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.chat.setting.ChatFrontEndSetting;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndSettingCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndUpdateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.cloud.CloudCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerUpdateDTO;

public interface ChatFrontEndSettingService {
  
  String addNewSetting(IChatUser user,FrontEndSettingCreateDTO dto);
  
  void updateSetting(FrontEndUpdateDTO dto);
  
  void updateIAgentServer(IAgentServer server, IAgentServerUpdateDTO dto);
  
  void updateCloud(String id, CloudCreateDTO dto);
  
  void updateFrontEnd(ChatFrontEnd chatFrontEnd, FrontEndUpdateDTO dto);
  
  void updateDemandInfo(String id, List<DemandInfo> demandInfo);


  ChatFrontEndSetting findSettingByChatFrontEndyId(String chatFrontEndId);

}

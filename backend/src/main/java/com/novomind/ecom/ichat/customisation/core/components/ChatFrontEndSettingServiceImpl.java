package com.novomind.ecom.ichat.customisation.core.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.chat.setting.ChatFrontEndSetting;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatFrontEndSettingDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatFrontEndManagementService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatFrontEndSettingService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.DemandInfoService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.IAgentServerService;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndSettingCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndUpdateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.cloud.CloudCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerUpdateDTO;

@Service
public class ChatFrontEndSettingServiceImpl implements ChatFrontEndSettingService {

  @Autowired
  IAgentServerService iAgentServerService;

  @Autowired
  ChatFrontEndManagementService chatFrontEndService;

  @Autowired
  DemandInfoService demandInfoService;

  @Autowired
  ChatFrontEndSettingDao settingDao;

  @Override
  public ChatFrontEndSetting findSettingByChatFrontEndyId(String chatFrontEndId) {
    return settingDao.findSettingByInfoChatFrontEndId(chatFrontEndId);
  }

  @Override
  public String addNewSetting(IChatUser user, FrontEndSettingCreateDTO dto) {
    String chatFrontEndId = chatFrontEndService.insertChatFrontEnd(user, dto.getFrontEndCreateDTO());
    List<DemandInfo> demandInfo = new ArrayList<>();
    String iAgentServerId = null;
    String cloudId = null;
    String demandInfoId = null;
    if (dto.getIAgentServerCreateDTO() != null)
      iAgentServerId = iAgentServerService.addNewIAgentServer(dto.getIAgentServerCreateDTO());
    if (dto.getDemandInfoCreateDTO() != null) {
      dto.getDemandInfoCreateDTO().stream()
          .forEach(demandInfoCreateDTO -> demandInfo.add(DemandInfo.of(demandInfoCreateDTO)));
      demandInfoId = demandInfoService.addDemandInFo(demandInfo);

    }

    ChatFrontEndSetting setting = ChatFrontEndSetting.builder().iAgentServerId(iAgentServerId).cloudId(cloudId)
        .urlPath(dto.getUrlPath()).demandInfoId(demandInfoId).build();

    return settingDao.insertNewSetting(chatFrontEndId, setting);
  }

  @Override
  public void updateSetting(FrontEndUpdateDTO dto) {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateIAgentServer(IAgentServer server, IAgentServerUpdateDTO dto) {
    iAgentServerService.updateInfo(server.getId(), dto);
  }

  @Override
  public void updateCloud(String id, CloudCreateDTO dto) {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateFrontEnd(ChatFrontEnd chatFrontEnd, FrontEndUpdateDTO dto) {
    chatFrontEndService.updateInfo(chatFrontEnd.getId(), dto);
  }

  @Override
  public void updateDemandInfo(String id, List<DemandInfo> demandInfo) {
    demandInfoService.updateDemandInfo(id, demandInfo);
  }

}

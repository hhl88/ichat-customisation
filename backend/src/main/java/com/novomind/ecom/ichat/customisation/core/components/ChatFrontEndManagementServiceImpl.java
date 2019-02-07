package com.novomind.ecom.ichat.customisation.core.components;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfoItem;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatFrontEndDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatFrontEndManagementService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.DemandInfoService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.IAgentServerService;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndUpdateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service("chatFrontEndManagementService")
public class ChatFrontEndManagementServiceImpl implements ChatFrontEndManagementService {

    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    ChatFrontEndDao chatFrontEndDao;

    @Autowired
    IAgentServerService iAgentServerService;

    @Autowired
    DemandInfoService demandInfoService;

    @Override
    public String insertChatFrontEnd(IChatUser user, FrontEndCreateDTO dto) {
        List<DemandInfoItem> demandInfo = new ArrayList<>();
        String iAgentServerId = null;
        String cloudId = null;
        String demandInfoId = null;
        if (dto.getIAgentServer() != null)
            iAgentServerId = iAgentServerService.addNewIAgentServer(IAgentServer.of(dto.getIAgentServer()));
        if (dto.getDemandInfo() != null) {
            dto.getDemandInfo()
                    .getDemandInfoList()
                    .forEach(demandInfoItemDTO -> demandInfo.add(DemandInfoItem.of(demandInfoItemDTO)));
            demandInfoId = demandInfoService.addDemandInFo(demandInfo);
        }
        ChatFrontEnd chatFrontEnd = ChatFrontEnd.of(dto);
        chatFrontEnd.setIAgentServerId(iAgentServerId);
        chatFrontEnd.setDemandInfoId(demandInfoId);
        chatFrontEnd.setCloudId(cloudId);
        chatFrontEnd.setUserId(user.getId());
        return chatFrontEndDao.insertChatFrontEnd(chatFrontEnd);
    }

    @Override
    public void update(ChatFrontEnd chatFrontEnd, FrontEndUpdateDTO dto) {
        log.info("dto " + dto);
        String iAgentServerId = chatFrontEnd.getIAgentServerId();
        String cloudId = chatFrontEnd.getCloudId();
        String demandInfoId = chatFrontEnd.getDemandInfoId();
        IAgentServer iAgentServer = IAgentServer.of(dto.getIAgentServer());

        List<DemandInfoItem> demandInfos = new ArrayList<>();
        if (dto.getDemandInfo() != null) {
            dto.getDemandInfo()
                    .getDemandInfoList()
                    .forEach(item -> demandInfos.add(DemandInfoItem.of(item)));
        }


        if (iAgentServerId != null) {
            iAgentServer.setId(iAgentServerId);
            iAgentServerService.updateInfo(iAgentServer);
        } else {
            iAgentServerId = iAgentServerService.addNewIAgentServer(iAgentServer);
            chatFrontEnd.setIAgentServerId(iAgentServerId);
        }

        if (demandInfoId != null) {
            DemandInfo demandInfo = new DemandInfo(demandInfoId, demandInfos);
            demandInfoService.updateDemandInfo(demandInfo);
        } else {
            if (!demandInfos.isEmpty()) {
                demandInfoId = demandInfoService.addDemandInFo(demandInfos);
                chatFrontEnd.setDemandInfoId(demandInfoId);
            }
        }

        chatFrontEnd.setUrlPath(dto.getUrlPath());
        chatFrontEnd.setName(dto.getName());
        chatFrontEnd.setConnectionType(dto.getConnectionType());
        chatFrontEndDao.updateChatFrontEnd(chatFrontEnd);
    }

    @Override
    public Optional<ChatFrontEnd> findChatFrontEndById(String id) {
        return chatFrontEndDao.findChatFrontEndById(id);
    }

    @Override
    public List<ChatFrontEnd> findChatFrontEndByUserId(String userId) {
        return chatFrontEndDao.findChatFrontEndByUserId(userId);
    }


}

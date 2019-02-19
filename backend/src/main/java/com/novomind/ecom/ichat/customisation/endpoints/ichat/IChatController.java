package com.novomind.ecom.ichat.customisation.endpoints.ichat;


import com.novomind.ecom.ichat.customisation.core.chat.Settings;
import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.*;

import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.core.utils.IChatUtils;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/ichats")
@Api(value = "/api/v1/ichats", tags = "IChat")
public class IChatController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IChatUserManagementService userManagementService;

    @Autowired
    ChatFrontEndManagementService chatFrontEndManagementService;


    @Autowired
    ChatLayoutService chatLayoutService;

    @Autowired
    ChooseLayoutService chooseLayoutService;

    @Autowired
    ChooseChatFrontEndService chooseChatFrontEndService;

    @Autowired
    IAgentServerService iAgentServerService;

    @Autowired
    DemandInfoService demandInfoService;

    @ApiOperation(value = "Get a settings by Id", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved settings"),
            @ApiResponse(code = 404, message = "Settings is not found"),
    })
    @GetMapping(value = "/{user_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Settings> getSettingsById(@PathVariable("user_id") String id) {

        log.info("user id " + id);

        String chatLayoutId = chooseLayoutService.findChatLayoutIdByUserId(id);
        String chatFrontEndId = chooseChatFrontEndService.findChatFrontEndIdByUserId(id);
        Optional<ChatFrontEnd> optChatFrontEnd = chatFrontEndManagementService.findChatFrontEndById(chatFrontEndId);
        Optional<ChatLayout> optChatLayout = chatLayoutService.findChatLayoutById(chatLayoutId);

        Settings settings = new Settings();
        if(optChatFrontEnd.isPresent()) {
            ChatFrontEnd chatFrontEnd = optChatFrontEnd.get();
            IAgentServer iAgentServer = null;
            DemandInfo demandInfo = null;
            if (chatFrontEnd.getIAgentServerId() != null) {
                iAgentServer = iAgentServerService
                        .findIAgentServerById(chatFrontEnd.getIAgentServerId())
                        .get();
            }

            if (chatFrontEnd.getDemandInfoId() != null)
                demandInfo = demandInfoService.findDemandInfoById(chatFrontEnd.getDemandInfoId()).get();
            FrontEndDTO dto = IChatUtils.convertToFrontEndDTO(chatFrontEnd, iAgentServer, demandInfo);

            settings.setChatFrontEnd(dto);
        }

        settings.setChatLayout(optChatLayout.isPresent() ? optChatLayout.get() : null);

        return ResponseEntity.status(HttpStatus.OK).body(settings);
    }

}

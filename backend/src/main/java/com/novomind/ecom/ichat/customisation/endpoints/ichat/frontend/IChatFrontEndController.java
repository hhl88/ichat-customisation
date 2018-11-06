package com.novomind.ecom.ichat.customisation.endpoints.ichat.frontend;

import java.security.Principal;

import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.DemandInfoService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.IAgentServerService;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.domain.dtos.IdDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndSettingDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndUpdateDTO;
import com.novomind.ecom.ichat.customisation.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatFrontEndManagementService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.IChatUserManagementService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndCreateDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/ichats/frontends")
public class IChatFrontEndController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IChatUserManagementService userManagementService;

    @Autowired
    ChatFrontEndManagementService chatFrontEndManagementService;

    @Autowired
    IAgentServerService iAgentServerService;

    @Autowired
    DemandInfoService demandInfoService;

    @ApiOperation(value = "Get a frontend by Id", response = FrontEndDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved frontend"),
            @ApiResponse(code = 404, message = "frontend is not found"),
            @ApiResponse(code = 401, message = "No Permission")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FrontEndDTO getFrontEndById(@PathVariable String id, Principal principal) throws UserNotFoundException, ChatFrontEndNotFoundException, NoPermissionException {
        ChatFrontEnd chatFrontEnd = findChatFrontEnd(principal.getName(), id);
        return convertToFrontEndDTO(chatFrontEnd);
    }


    @ApiOperation(value = "Get a frontend setting by Id", response = FrontEndSettingDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved frontend setting"),
            @ApiResponse(code = 404, message = "Frontend is not found"),
            @ApiResponse(code = 401, message = "No Permission")
    })
    @GetMapping("/{id}/settings")
    @ResponseStatus(HttpStatus.OK)
    public FrontEndSettingDTO getFrontEndSetting(@PathVariable String id, Principal principal) throws UserNotFoundException, ChatFrontEndNotFoundException, IAgentServerNotFoundException, DemandInfoNotFoundException, NoPermissionException {
        ChatFrontEnd chatFrontEnd = findChatFrontEnd(principal.getName(), id);
        IAgentServer iAgentServer = iAgentServerService.findIAgentServerById(chatFrontEnd.getIAgentServerId()).orElseThrow(() -> new IAgentServerNotFoundException(chatFrontEnd.getIAgentServerId()));
        DemandInfo demandInfo = demandInfoService.findDemandInfoById(chatFrontEnd.getDemandInfoId()).orElseThrow(() -> new DemandInfoNotFoundException(chatFrontEnd.getDemandInfoId()));

        return convertToFrontEndSettingDTO(chatFrontEnd, iAgentServer, demandInfo);
    }


    @ApiOperation(value = "Update a frontend")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Frontend successfully updated"),
            @ApiResponse(code = 401, message = "No Permission"),
            @ApiResponse(code = 404, message = "Frontend is not found")
    })
    @PutMapping("/{id}")
    public void updateChatFrontEnd(@PathVariable(value = "id") String id,
                                   @RequestBody FrontEndUpdateDTO dto,
                                   Principal principal) throws UserNotFoundException, ChatFrontEndNotFoundException,  NoPermissionException{
        ChatFrontEnd chatFrontEnd = findChatFrontEnd(principal.getName(), id);
        chatFrontEndManagementService.update(chatFrontEnd, dto);
    }

    @ApiOperation(value = "Create a frontend", response = IdDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved frontend"),
            @ApiResponse(code = 400, message = "Invalid arguments"),
    })
    @PostMapping("")
    public IdDTO createFrontEnd(@RequestBody FrontEndCreateDTO dto, Principal principal) throws UserNotFoundException {
        IChatUser user = userManagementService.findIChatUserByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("user_not_found"));
        return new IdDTO(chatFrontEndManagementService.insertChatFrontEnd(user, dto));
    }

    private ChatFrontEnd findChatFrontEnd(String userEmail, String frontEndId) throws UserNotFoundException, ChatFrontEndNotFoundException, NoPermissionException {
        IChatUser user = userManagementService.findIChatUserByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));
        ChatFrontEnd chatFrontEnd = chatFrontEndManagementService.findChatFrontEndById(frontEndId)
                .orElseThrow(() -> new ChatFrontEndNotFoundException(frontEndId));
        if (!user.getId().equals(chatFrontEnd.getUserId()))
            throw new NoPermissionException("no_permission");
        return chatFrontEnd;
    }

    private FrontEndDTO convertToFrontEndDTO(ChatFrontEnd chatFrontEnd) {
        return new FrontEndDTO(chatFrontEnd.getId(), chatFrontEnd.getName());

    }

    private FrontEndSettingDTO convertToFrontEndSettingDTO(ChatFrontEnd chatFrontend, IAgentServer iAgentServer, DemandInfo demandInfo) {
        return FrontEndSettingDTO.builder()
                .id(chatFrontend.getId())
                .urlPath(chatFrontend.getUrlPath())
                .iAgentServer(iAgentServer)
                .connectionType(chatFrontend.getConnectionType())
                .demandInfo(demandInfo)
                .build();
    }

}

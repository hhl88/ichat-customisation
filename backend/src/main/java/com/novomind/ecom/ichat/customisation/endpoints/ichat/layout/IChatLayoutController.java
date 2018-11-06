package com.novomind.ecom.ichat.customisation.endpoints.ichat.layout;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.*;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.IdDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndSettingDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndUpdateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutSettingDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutUpdateDTO;
import com.novomind.ecom.ichat.customisation.exceptions.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/ichats/layouts")
public class IChatLayoutController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IChatUserManagementService userManagementService;

    @Autowired
    ChatLayoutService chatLayoutService;


    @ApiOperation(value = "Get a layout by Id", response = ChatLayoutDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved layout"),
            @ApiResponse(code = 404, message = "Layout is not found"),
            @ApiResponse(code = 401, message = "No Permission")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChatLayoutDTO getChatLayoutById(@PathVariable String id, Principal principal) throws UserNotFoundException, ChatLayoutNotFoundException, NoPermissionException {
        ChatLayout chatLayout = findChatLayout(principal.getName(), id);
        return convertToChatLayoutDTO(chatLayout);
    }


    @ApiOperation(value = "Get a layout setting by Id", response = ChatLayoutSettingDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved layout setting"),
            @ApiResponse(code = 404, message = "Layout is not found"),
            @ApiResponse(code = 401, message = "No Permission")
    })
    @GetMapping("/{id}/settings")
    @ResponseStatus(HttpStatus.OK)
    public ChatLayoutSettingDTO getFrontEndSetting(@PathVariable String id, Principal principal) throws UserNotFoundException, ChatLayoutNotFoundException,  NoPermissionException {
        ChatLayout chatLayout = findChatLayout(principal.getName(), id);

        return convertToChatLayoutSettingDTO(chatLayout);
    }

    @ApiOperation(value = "Update a layout")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Layout successfully updated"),
            @ApiResponse(code = 401, message = "No Permission"),
            @ApiResponse(code = 404, message = "Layout is not found")
    })
    @PutMapping("/{id}")
    public void updateChatFrontEnd(@PathVariable(value = "id") String id,
                                   @RequestBody ChatLayoutUpdateDTO dto,
                                   Principal principal) throws UserNotFoundException, ChatLayoutNotFoundException,  NoPermissionException{
        ChatLayout chatLayout = findChatLayout(principal.getName(), id);
        chatLayoutService.updateInfo(chatLayout, dto);
    }

    @ApiOperation(value = "Create a layout", response = IdDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved layout"),
            @ApiResponse(code = 400, message = "Invalid arguments"),
    })
    @PostMapping("")
    public IdDTO createFrontEnd(@RequestBody ChatLayoutCreateDTO dto, Principal principal) throws UserNotFoundException {
        IChatUser user = userManagementService.findIChatUserByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("user_not_found"));
        return new IdDTO(chatLayoutService.insertNewChatLayout(user, dto));
    }

    private ChatLayout findChatLayout(String userEmail, String chatLayoutId) throws UserNotFoundException, ChatLayoutNotFoundException, NoPermissionException {
        IChatUser user = userManagementService.findIChatUserByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));
        ChatLayout chatLayout = chatLayoutService.findChatLayoutById(chatLayoutId)
                .orElseThrow(() -> new ChatLayoutNotFoundException(chatLayoutId));
        if (!user.getId().equals(chatLayout.getUserId()))
            throw new NoPermissionException("no_permission");
        return chatLayout;
    }

    private ChatLayoutDTO convertToChatLayoutDTO(ChatLayout chatLayout) {
        return new ChatLayoutDTO(chatLayout.getId(), chatLayout.getName());

    }

    private ChatLayoutSettingDTO convertToChatLayoutSettingDTO(ChatLayout chatLayout) {
        return ChatLayoutSettingDTO.builder()
                .layoutDisplay(chatLayout.getDisplayType())
                .textAreaDisplay(chatLayout.getTextInputType())
                .buttonDisplay(chatLayout.getButtonType())
                .logoImage(chatLayout.getLogo())
                .backgroundImage(chatLayout.getBackgroundImg())
                .backgroundDisplay(chatLayout.getBackgroundType())
                .font(chatLayout.getFont())
                .build();
    }

}

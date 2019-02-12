package com.novomind.ecom.ichat.customisation.endpoints.ichat.layout;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatLayoutService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.IChatUserManagementService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.IdDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutUpdateDTO;
import com.novomind.ecom.ichat.customisation.exceptions.ChatLayoutNotFoundException;
import com.novomind.ecom.ichat.customisation.exceptions.NoPermissionException;
import com.novomind.ecom.ichat.customisation.exceptions.UserNotFoundException;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ichats/layouts")
@Api(value = "/api/v1/ichats/layouts", tags = "IChat Layout")
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
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChatLayoutDTO getChatLayoutById(@PathVariable String id) throws  ChatLayoutNotFoundException {
        log.info("getChatlayout " + id);
        ChatLayout chatLayout = chatLayoutService.findChatLayoutById(id).orElseThrow(() -> new ChatLayoutNotFoundException(id));
        log.info("chatlayout " + chatLayout);

        return convertToChatLayoutDTO(chatLayout);
    }


    @ApiOperation(value = "Get layouts", response = ChatLayoutDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved layouts"),
    })
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ChatLayoutDTO> getLayouts(Principal principal) throws UserNotFoundException {
        IChatUser user = userManagementService.findIChatUserByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException(principal.getName()));
        List<ChatLayout> chatLayouts = chatLayoutService.findChatLayoutByUserId(user.getId());
        List<ChatLayoutDTO> chatLayoutDTOs = new ArrayList<>();
        chatLayouts
                .forEach(chatLayout -> chatLayoutDTOs.add(convertToChatLayoutDTO(chatLayout)));
        return chatLayoutDTOs;
    }

    @ApiOperation(value = "Update a layout")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Layout successfully updated"),
            @ApiResponse(code = 401, message = "No Permission"),
            @ApiResponse(code = 404, message = "Layout is not found")
    })
    @PutMapping(value="/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> updateChatLayout(@PathVariable(value = "id") String id,
                                              @Valid @RequestParam ChatLayoutUpdateDTO dto,
                                              @RequestParam(value = "logo", required = false) MultipartFile logo,
                                              @RequestParam(value = "backgroundImg", required = false) MultipartFile backgroundImg,
                                              Principal principal) throws UserNotFoundException, ChatLayoutNotFoundException, NoPermissionException {
        ChatLayout chatLayout = findChatLayout(principal.getName(), id);
        chatLayoutService.updateInfo(chatLayout, dto, logo, backgroundImg);
        return ResponseEntity.status(HttpStatus.OK).body("Layout successfully updated");
    }

    @ApiOperation(value = "Create a layout")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved layout"),
            @ApiResponse(code = 400, message = "Invalid arguments"),
    })
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<IdDTO> createChatLayout(@Valid @NotNull ChatLayoutCreateDTO data,
                                                  BindingResult bindingResult,
                                                  Principal principal) throws UserNotFoundException {
        bindingResult.getSuppressedFields();
        log.info("binding " + bindingResult.getSuppressedFields());
        IChatUser user = userManagementService.findIChatUserByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("user_not_found"));
        String id = chatLayoutService.insertNewChatLayout(user, data, data.getLogo(), data.getBackgroundImg());
        return ResponseEntity.status(HttpStatus.OK).body(new IdDTO(id));
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
        return ChatLayoutDTO.of(chatLayout);

    }

}

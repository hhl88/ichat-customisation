package com.novomind.ecom.ichat.customisation.endpoints.ichat.layout;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatLayoutService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChooseLayoutService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.IChatUserManagementService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.StorageService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/layouts")
@Api(value = "/api/v1/layouts", tags = "IChat Layout")
public class IChatLayoutController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IChatUserManagementService userManagementService;

    @Autowired
    ChatLayoutService chatLayoutService;

    @Autowired
    ChooseLayoutService chooseLayoutService;

    @Autowired
    StorageService storageService;


    @ApiOperation(value = "Get layouts", response = ChatLayout.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved layouts"),
    })
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ChatLayout> getLayouts(Principal principal) throws UserNotFoundException {
        IChatUser user = userManagementService.findIChatUserByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException(principal.getName()));
        List<ChatLayout> chatLayouts = chatLayoutService.findChatLayoutByUserId(user.getId());
//        List<ChatLayoutDTO> chatLayoutDTOs = new ArrayList<>();
//        chatLayouts
//                .forEach(chatLayout -> chatLayoutDTOs.add(convertToChatLayoutDTO(chatLayout)));
        return chatLayouts;
    }

    @ApiOperation(value = "Update a layout", response = ChatLayout.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Layout successfully updated"),
            @ApiResponse(code = 401, message = "No Permission"),
            @ApiResponse(code = 404, message = "Layout is not found")
    })
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = MediaType.ALL_VALUE)
    public ChatLayout updateChatLayout(@PathVariable(value = "id") String id,
                                       @Valid @NotNull @RequestPart("layoutDto") ChatLayoutUpdateDTO chatLayoutUpdateDTO,
                                       @RequestPart(value = "logo", required = false) MultipartFile logo,
                                       @RequestPart(value = "backgroundImg", required = false) MultipartFile backgroundImg,
                                       Principal principal) throws UserNotFoundException, ChatLayoutNotFoundException, NoPermissionException, IOException {
        log.info("update " + chatLayoutUpdateDTO);
        ChatLayout chatLayout = findChatLayout(principal.getName(), id);
        return chatLayoutService.updateInfo(chatLayout, chatLayoutUpdateDTO, logo, backgroundImg);
    }

    @ApiOperation(value = "Create a layout", response = ChatLayout.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully retrieved layout"),
            @ApiResponse(code = 400, message = "Invalid arguments"),
    })
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.ALL_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ChatLayout createChatLayout(@Valid @NotNull @RequestPart("layoutDto") ChatLayoutCreateDTO chatLayoutCreateDTO,
                                       @RequestPart(value = "logo", required = false) MultipartFile logo,
                                       @RequestPart(value = "backgroundImg", required = false) MultipartFile backgroundImg,
                                       Principal principal) throws UserNotFoundException, IOException {
        IChatUser user = userManagementService.findIChatUserByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("user_not_found"));
        return chatLayoutService.insertNewChatLayout(user, chatLayoutCreateDTO, logo, backgroundImg);
    }

    @ApiOperation(value = "Get a logo by chat layout Id", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved logo"),
            @ApiResponse(code = 404, message = "Layout is not found"),
    })
    @GetMapping("/{id}/logoImg")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InputStreamResource> getLogoImg(@PathVariable("id") String id) throws ChatLayoutNotFoundException, IOException {
        ChatLayout chatLayout = chatLayoutService.findChatLayoutById(id)
                .orElseThrow(() -> new ChatLayoutNotFoundException(id));
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(storageService.loadFile(chatLayout.getLogo()).getInputStream()));
    }

    @ApiOperation(value = "Get a background image by chat layout Id", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved background image"),
            @ApiResponse(code = 404, message = "Layout is not found"),
    })
    @GetMapping(value = "/{id}/backgroundImg", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InputStreamResource> getBackgroundImg(@PathVariable("id") String id) throws ChatLayoutNotFoundException, IOException {
        ChatLayout chatLayout = chatLayoutService.findChatLayoutById(id)
                .orElseThrow(() -> new ChatLayoutNotFoundException(id));
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(storageService.loadFile(chatLayout.getBackgroundImg()).getInputStream()));
    }


    @ApiOperation(value = "set a default frontend", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Frontend successfully set as default"),
            @ApiResponse(code = 401, message = "No Permission"),
            @ApiResponse(code = 404, message = "Frontend is not found")
    })
    @PutMapping(value = "/{id}/default",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity setDefaultFrontEnd(@PathVariable String id, Principal principal) throws ChatLayoutNotFoundException, UserNotFoundException, NoPermissionException{
        ChatLayout chatLayout = findChatLayout(principal.getName(), id);
        chooseLayoutService.setAsDefault(chatLayout);
        return  ResponseEntity.status(HttpStatus.OK).build();
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

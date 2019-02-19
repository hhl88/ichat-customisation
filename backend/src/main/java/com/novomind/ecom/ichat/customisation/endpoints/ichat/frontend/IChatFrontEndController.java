package com.novomind.ecom.ichat.customisation.endpoints.ichat.frontend;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.*;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.core.utils.IChatUtils;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Connection;
import com.novomind.ecom.ichat.customisation.domain.dtos.IdDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndUpdateDTO;
import com.novomind.ecom.ichat.customisation.exceptions.ChatFrontEndNotFoundException;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/frontends")
@Api(value = "/api/v1/frontends", tags = "IChat Frontend")
public class IChatFrontEndController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IChatUserManagementService userManagementService;

    @Autowired
    ChatFrontEndManagementService chatFrontEndManagementService;

    @Autowired
    ChooseChatFrontEndService chooseChatFrontEndService;

    @Autowired
    IAgentServerService iAgentServerService;

    @Autowired
    DemandInfoService demandInfoService;


    @ApiOperation(value = "Get frontends", response = FrontEndDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved frontends"),
    })
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<FrontEndDTO> getFrontEnds(Principal principal) throws UserNotFoundException {
        IChatUser user = userManagementService.findIChatUserByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException(principal.getName()));
        List<ChatFrontEnd> chatFrontEnds = chatFrontEndManagementService.findChatFrontEndByUserId(user.getId());
        List<FrontEndDTO> frontEndDTOS = new ArrayList<>();
        chatFrontEnds
                .forEach(chatFrontEnd -> {
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
                    log.info("dto " + dto);
                    frontEndDTOS.add(dto);
                });
        return frontEndDTOS;
    }


    @ApiOperation(value = "Update a frontend")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Frontend successfully updated"),
            @ApiResponse(code = 401, message = "No Permission"),
            @ApiResponse(code = 404, message = "Frontend is not found")
    })
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> updateChatFrontEnd(@PathVariable(value = "id") String id,
                                                @Valid @NotNull @RequestBody FrontEndUpdateDTO dto,
                                                Principal principal) throws UserNotFoundException, ChatFrontEndNotFoundException, NoPermissionException {
        ChatFrontEnd chatFrontEnd = findChatFrontEnd(principal.getName(), id);
        chatFrontEndManagementService.update(chatFrontEnd, dto);
        return ResponseEntity.status(HttpStatus.OK).body("Frontend successfully updated");
    }

    @ApiOperation(value = "Create a frontend")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved frontend"),
            @ApiResponse(code = 400, message = "Invalid arguments"),
    })
    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<ChatFrontEnd> createFrontEnd(@Valid @NotNull @RequestBody FrontEndCreateDTO dto, Principal principal) throws UserNotFoundException {
        log.info("createrFrontEnd " + dto);
        IChatUser user = userManagementService.findIChatUserByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("user_not_found"));
        String id = chatFrontEndManagementService.insertChatFrontEnd(user, dto);
        ChatFrontEnd chatFrontEnd = chatFrontEndManagementService.findChatFrontEndById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(chatFrontEnd);

    }


    @ApiOperation(value = "set a default frontend", response = ChatFrontEnd.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Frontend successfully set as default"),
            @ApiResponse(code = 401, message = "No Permission"),
            @ApiResponse(code = 404, message = "Frontend is not found")
    })
    @PutMapping(value = "/{id}/default", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity setDefaultFrontEnd(@PathVariable String id, Principal principal) throws ChatFrontEndNotFoundException, UserNotFoundException, NoPermissionException {
        ChatFrontEnd chatFrontEnd = findChatFrontEnd(principal.getName(), id);
        chooseChatFrontEndService.setAsDefault(chatFrontEnd);
        return ResponseEntity.status(HttpStatus.OK).build();
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

}

package com.novomind.ecom.ichat.customisation.endpoints.ichat.frontend;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatFrontEndManagementService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.DemandInfoService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.IAgentServerService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.IChatUserManagementService;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.IdDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndUpdateDTO;
import com.novomind.ecom.ichat.customisation.exceptions.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ichats/frontends")
@Api(value = "/api/v1/ichats/frontends", tags = "IChat Frontend")
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
                    FrontEndDTO dto = convertToFrontEndDTO(chatFrontEnd);
                    log.info("dto " +  dto);
                    frontEndDTOS.add(convertToFrontEndDTO(chatFrontEnd));
                });
        return frontEndDTOS;
    }


    @ApiOperation(value = "Update a frontend")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Frontend successfully updated"),
            @ApiResponse(code = 401, message = "No Permission"),
            @ApiResponse(code = 404, message = "Frontend is not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateChatFrontEnd(@PathVariable(value = "id") String id,
                                                @Valid @NotNull @RequestBody FrontEndUpdateDTO dto,
                                                Principal principal) throws UserNotFoundException, ChatFrontEndNotFoundException, NoPermissionException {
        log.info("update " + dto);
        ChatFrontEnd chatFrontEnd = findChatFrontEnd(principal.getName(), id);
        chatFrontEndManagementService.update(chatFrontEnd, dto);
        return ResponseEntity.status(HttpStatus.OK).body("Frontend successfully updated");
    }

    @ApiOperation(value = "Create a frontend")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved frontend"),
            @ApiResponse(code = 400, message = "Invalid arguments"),
    })
    @PostMapping("")
    public ResponseEntity<IdDTO> createFrontEnd(@Valid @NotNull @RequestBody FrontEndCreateDTO dto, Principal principal) throws UserNotFoundException {
        log.info("createrFrontEnd " + dto);
        IChatUser user = userManagementService.findIChatUserByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("user_not_found"));
        String id = chatFrontEndManagementService.insertChatFrontEnd(user, dto);
        return ResponseEntity.status(HttpStatus.OK).body(new IdDTO(id));

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
        IAgentServer iAgentServer = null;
        DemandInfo demandInfo = null;
        if (chatFrontEnd.getIAgentServerId() != null)
            iAgentServer = iAgentServerService
                    .findIAgentServerById(chatFrontEnd.getIAgentServerId())
                    .get();
        if (chatFrontEnd.getDemandInfoId() != null)
            demandInfo = demandInfoService.findDemandInfoById(chatFrontEnd.getDemandInfoId()).get();

        return new FrontEndDTO(chatFrontEnd.getId(), chatFrontEnd.getName(), iAgentServer, chatFrontEnd.getUrlPath(), demandInfo, String.valueOf(chatFrontEnd.getConnectionType().ordinal()));

    }


}

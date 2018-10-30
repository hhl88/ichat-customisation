package com.novomind.ecom.ichat.customisation.endpoints;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatFrontEndManagementService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.IChatUserManagementService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndCreateDTO;
import com.novomind.ecom.ichat.customisation.exceptions.NoPermissionException;
import com.novomind.ecom.ichat.customisation.exceptions.NotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/ichats")
public class ChatCustomisationController {
  
  Logger log = LoggerFactory.getLogger(getClass());
  
  @Autowired
  IChatUserManagementService userManagementService;

  
  @Autowired
  ChatFrontEndManagementService chatFrontEndManagementService;
  
  @ApiOperation(value = "Get a frontend by Id", response = IChatUser.class)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved frontend"),
      @ApiResponse(code = 404, message = "frontend is not found") })
  @GetMapping("/frontends/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ChatFrontEnd getUserInfoById(@PathVariable String id, Principal principal) {
    IChatUser user = userManagementService.findIChatUserByEmail(principal.getName())
        .orElseThrow(() -> new NotFoundException("user_not_found"));
    
    ChatFrontEnd chatFrontEnd = chatFrontEndManagementService.findChatFrontEndById(id)
        .orElseThrow(() -> new NotFoundException("user with id = " + id + " is not found"));
    
    if (user.getId().equals(chatFrontEnd.getUserId()))
        return chatFrontEnd;
    throw new NoPermissionException("no_permission");
  }
  
  
  @ApiOperation(value = "Create a frontend setting", response = ChatFrontEnd.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved frontend"),
          @ApiResponse(code = 400, message = "Invalid arguments"),
  })
  @PostMapping("/frontends")
  public String createFrontEnd(@RequestBody FrontEndCreateDTO dto, Principal principal) {
    IChatUser user = userManagementService.findIChatUserByEmail(principal.getName())
        .orElseThrow(() -> new NotFoundException("user_not_found"));
    
      return chatFrontEndManagementService.insertChatFrontEnd(user, dto);
  }

}

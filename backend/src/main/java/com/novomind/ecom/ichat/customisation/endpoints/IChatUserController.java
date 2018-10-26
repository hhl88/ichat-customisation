package com.novomind.ecom.ichat.customisation.endpoints;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.novomind.ecom.ichat.customisation.core.interfaces.services.IChatUserManagementService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.user.UserCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.user.UserPasswordUpdateDTO;
import com.novomind.ecom.ichat.customisation.exceptions.NotFoundException;

@RestController
@RequestMapping("/api/v1/users")
public class IChatUserController {

  Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  IChatUserManagementService userManagementService;

  @ApiOperation(value = "Get a user by Id", response = IChatUser.class)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved user"),
      @ApiResponse(code = 404, message = "user is not found") })
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public IChatUser getUserInfoById(@PathVariable String id) throws NotFoundException {
    log.info("get user ID");
    return userManagementService.findIChatUserById(id)
        .orElseThrow(() -> new NotFoundException("user with id = " + id + " is not found"));
  }

  @ApiOperation(value = "Create a user")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created user"),
      @ApiResponse(code = 409, message = "dupplicated email"),

  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> register(@Valid @RequestBody UserCreateDTO userCreateDTO) {
    Optional<IChatUser> user = userManagementService.findIChatUserByEmail(userCreateDTO.getEmail());
    if (!user.isPresent()) {
      userManagementService.register(IChatUser.of(userCreateDTO));
      return ResponseEntity.status(HttpStatus.OK).body("Successfully created user");
    }

    return ResponseEntity.status(HttpStatus.CONFLICT).body("dupplicated email");
  }

  @ApiOperation(value = "Update password")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated user"),
      @ApiResponse(code = 404, message = "user is not found") })
  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void updateUserPassword(@Valid @RequestBody UserPasswordUpdateDTO userPasswordUpdateDTO, Principal principal)
      throws NotFoundException {
    IChatUser user = getUserIfAccessAllowed(principal);

    userManagementService.updatePassword(user, userPasswordUpdateDTO.getOldPassword(),
        userPasswordUpdateDTO.getNewPassword(), userPasswordUpdateDTO.getRetypedPassword());
  }

  private IChatUser getUserIfAccessAllowed(Principal principal) throws NotFoundException {
    return userManagementService.findIChatUserByEmail(principal.getName())
        .orElseThrow(() -> new NotFoundException("User is not found"));

  }

}

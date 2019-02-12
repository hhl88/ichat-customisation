package com.novomind.ecom.ichat.customisation.endpoints.user;

import com.novomind.ecom.ichat.customisation.core.interfaces.services.IChatUserManagementService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.user.UserCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.user.UserPasswordUpdateDTO;
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
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@Api(value = "/api/v1/users", tags = "Users")
public class IChatUserController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IChatUserManagementService userManagementService;

    @ApiOperation(value = "Get a user by Id", response = IChatUser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 404, message = "User is not found")})
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public IChatUser getUserInfoById(@PathVariable String id) throws UserNotFoundException {
        log.info("get user ID");
        return userManagementService.findIChatUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }


    @ApiOperation(value = "Create a user", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created user"),
            @ApiResponse(code = 409, message = "duplicated email"),

    })
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        Optional<IChatUser> user = userManagementService.findIChatUserByEmail(userCreateDTO.getEmail());
        if (!user.isPresent()) {
            userManagementService.register(IChatUser.of(userCreateDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created user");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("duplicated email");
    }


    @ApiOperation(value = "Update password", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated user"),
            @ApiResponse(code = 401, message = "Wrong authentication"),
            @ApiResponse(code = 404, message = "User is not found")})
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateUserPassword(@PathVariable String id, @Valid @RequestBody UserPasswordUpdateDTO userPasswordUpdateDTO, Principal principal)
            throws UserNotFoundException, NoPermissionException {
        log.info("id " + id);
        ;
        IChatUser user = getUserIfAccessAllowed(principal);
        log.info("user " + user);
        if (!id.equalsIgnoreCase(user.getId())) {
            throw new NoPermissionException();
        }

        userManagementService.updatePassword(user, userPasswordUpdateDTO.getOldPassword(),
                userPasswordUpdateDTO.getNewPassword(), userPasswordUpdateDTO.getRetypedPassword());
        return ResponseEntity.status(HttpStatus.OK).body("Successfully updated password");

    }

    private IChatUser getUserIfAccessAllowed(Principal principal) throws UserNotFoundException {
        return userManagementService.findIChatUserByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("User is not found"));
    }

}

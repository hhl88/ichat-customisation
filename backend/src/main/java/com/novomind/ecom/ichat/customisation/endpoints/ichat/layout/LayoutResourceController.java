package com.novomind.ecom.ichat.customisation.endpoints.ichat.layout;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatLayoutService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.StoreService;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutDTO;
import com.novomind.ecom.ichat.customisation.exceptions.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/r/ichats/layouts")
@Api(value = "/r/ichats/layouts", tags = "Layout resource")
public class LayoutResourceController {
    @Autowired
    ChatLayoutService chatLayoutService;
    @Autowired
    StoreService storeService;


    @ApiOperation(value = "Get a logo of layout", response = Resource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved logo"),
            @ApiResponse(code = 404, message = "Logo is not found"),
    })
    @GetMapping( value = "/{id}/l", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Resource viewLogo(@PathVariable("id") String id) throws ResourceNotFoundException {
        Optional<ChatLayout> chatLayout = chatLayoutService.findChatLayoutById(id);
        if (!chatLayout.isPresent()) throw new ResourceNotFoundException();

        return storeService.loadFile(chatLayout.get().getLogo());
    }


    @ApiOperation(value = "Get a background image of layout", response = Resource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved background image"),
            @ApiResponse(code = 404, message = "Background image is not found"),
    })
    @GetMapping( value = "/{id}/b", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Resource viewBackgroundImage(@PathVariable("id") String id) throws ResourceNotFoundException {
        Optional<ChatLayout> chatLayout = chatLayoutService.findChatLayoutById(id);
        if (!chatLayout.isPresent()) throw new ResourceNotFoundException();

        return storeService.loadFile(chatLayout.get().getBackgroundImg());
    }

}

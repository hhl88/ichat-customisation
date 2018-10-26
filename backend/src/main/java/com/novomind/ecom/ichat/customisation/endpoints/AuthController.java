package com.novomind.ecom.ichat.customisation.endpoints;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novomind.ecom.ichat.customisation.core.interfaces.services.IChatUserManagementService;
import com.novomind.ecom.ichat.customisation.exceptions.NotFoundException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  
  Logger log = LoggerFactory.getLogger(getClass());
  
  @Autowired
  IChatUserManagementService userManagementService;
  
  @GetMapping("/me")
  public Map<String, Object> getCurrentUser( @AuthenticationPrincipal Authentication authentication) throws NotFoundException {
    log.info("getCurrentUser  "+ authentication);
    OAuth2AuthenticationDetails oAuth2AuthenticationDetails  = (OAuth2AuthenticationDetails ) authentication.getDetails();
    Map<String, Object> o = (Map<String, Object>) oAuth2AuthenticationDetails.getDecodedDetails();
    return o;
  }

}

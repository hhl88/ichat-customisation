package com.novomind.ecom.ichat.customisation.auth;

import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import java.util.HashMap;
import java.util.Map;


public class CustomTokenServices extends DefaultTokenServices {


    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
        final CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<String, Object>();
        IChatUser user = userDetails.getUser();
        additionalInfo.put("email", user.getEmail());
        additionalInfo.put("user_id", user.getId());
        authentication.setDetails(additionalInfo);
        return super.createAccessToken(authentication);
    }
}

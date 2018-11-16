package com.novomind.ecom.ichat.customisation.configs;

import com.novomind.ecom.ichat.customisation.auth.CustomUserDetails;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
        if (authentication.getOAuth2Request().getGrantType().equalsIgnoreCase("password")) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            final Map<String, Object> additionalInfo = new HashMap<String, Object>();
            IChatUser user = userDetails.getUser();
            additionalInfo.put("user_id", user.getId());
            additionalInfo.put("email", user.getEmail());
            

            authentication.setDetails(additionalInfo);

            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        }
        accessToken = super.enhance(accessToken, authentication);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(new HashMap<>());
        return accessToken;
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        final OAuth2Authentication authentication =    super.extractAuthentication(map);
        final Map<String, String> details = new HashMap<>();

        details.put("user_id", (String)map.get("user_id"));
        details.put("email", (String)map.get("email"));

        authentication.setDetails(details);
        return authentication;
    }
}

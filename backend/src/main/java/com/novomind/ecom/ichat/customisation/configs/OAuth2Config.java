package com.novomind.ecom.ichat.customisation.configs;

import com.novomind.ecom.ichat.customisation.auth.CustomTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpQIBAAKCAQEAxLwQxCpwYQtKou+wuQU9DHZailk4BMWSqE40SxjVph2g6lBP\n" +
            "I1Q6SEq3eZbiiMVMT0naUwfiUVLJYizEqJW3CD36sSmOq52l5B1qnTOdjUFMAY+D\n" +
            "d2a6e3M1U+5d/NA0sU0IuGI66tyhzPhPpCZsmEiYRUqtLl2XWEAqIe/kGnxFkic6\n" +
            "rwf6Ks5eNnL/8r99Wk22xf+C/ZQkzHKfm1cBSiObqjDOgbEXWIODpt4v+aKJeYEE\n" +
            "EBnJo6231JeHSr7tL3y0erTZkong69Oc503Q/1PX0Ivt5MnWYbeMn4a6MvogGlli\n" +
            "PC0B5BY4pLNwM2aWdkWflh6XLch1YC98JyPe+wIDAQABAoIBAQCCsjWpPFBgB/JT\n" +
            "gEy0/cfqyY2or1DJyr99VEblROBadQB76VJJtCDr+ccd3aEtH3VsjzGdS3WMdSUY\n" +
            "NYmIPb3Umeg7XdJMLHUSsIAtYybdum1V9BNwdSyZ6pWY5phF5cO7reOHKqrdcCmF\n" +
            "U8kE0gn+uOt/zPZ0APUUSbwzw3yUIqRcteBsoBm3QAx73BdinYbvq+dons3gMuD1\n" +
            "KCCrn21X8jJFaR3IvHrW6Fyoj5sG/9lEsN3nlkH4nJ3hG36KQXZ2fcF4qdzTSBzy\n" +
            "oWuTtbXrL3SS5iVP1n7XQf6UqyM9k9lOg4qr2J2whhOFwdTMVDOTF13lwaNybMbL\n" +
            "V6IOzAXhAoGBAOn66awbvpqAUKaLC2Kzi+L6r9JD502Lu9WciD2DVwTXLf8b/BeY\n" +
            "mElppcxkZEuyJQbvWvUV2egrjltYKoqJ/R50walScy+fbYO9AtY8pkbXrf2eH+oh\n" +
            "xwQcgTs2j3cJO0GZnoUueF6HHsCSmNKh6UGDm9Aup00kWkRXHZgRRZH5AoGBANc/\n" +
            "1AF0S91h7Cfb5iGNfkTWTc0+f2wHKsAy9HlhNFLRKJDPSdoGuuqVaeVOSA5RL96v\n" +
            "xIHSTObHkYvkPsv2fN37cZqE3oIErKrsPavD5S/bh7OWWlOfeVJBEVuXE+7yVqv3\n" +
            "+0PinCgpDGC0c8+F7383iQGTqYUqOjGxeuXq3LWTAoGAeCTzIAEPGl6RCzOSoRf4\n" +
            "2JBI//sGJnd99+3st0ldwYqMf9Zq1+LvL6MkE5PpPEU5Zho55mdWATV8X8Mh9Qlu\n" +
            "A9mIJ93Cb825ek/vyPpTl0Tgfr5XPO4jNyU75p/ZgxImb0TmdRSrkj5nuBKOjnm9\n" +
            "QHrH41pRZfcosrbY52DXDGECgYEAwE7OYt48SXYLPzE/0gpQHcA5kn2b6jAdCcp8\n" +
            "kVU/Ih6sV3RbTudkZ+FF0xC1X9U5J4Nx1WrGOgljB9DMKf32YX0caij7gr4aZjra\n" +
            "fnNud+tyughCeyOniM2JbIL11C2/MbVET8pOKbNC08NlFKtc5hP/2+DJO6gWhf9D\n" +
            "BddniPECgYEAmxL49QFzr0ADDUqJ9MMD3yDpwstE0bmsNT9wH6kaRZN4mLo4u67l\n" +
            "H6/WOhK/v7bDHR+38ARxP/FfeCOhgogx/9qP0oSr2rxGavA77of5ufwQF+6IfFJN\n" +
            "aOWduE5aa5tSWeRaa46ZEPoZZqu8zF2bdvuoiZNSp+mTcfY42jcJ2wo=\n" +
            "-----END RSA PRIVATE KEY-----";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxLwQxCpwYQtKou+wuQU9\n" +
            "DHZailk4BMWSqE40SxjVph2g6lBPI1Q6SEq3eZbiiMVMT0naUwfiUVLJYizEqJW3\n" +
            "CD36sSmOq52l5B1qnTOdjUFMAY+Dd2a6e3M1U+5d/NA0sU0IuGI66tyhzPhPpCZs\n" +
            "mEiYRUqtLl2XWEAqIe/kGnxFkic6rwf6Ks5eNnL/8r99Wk22xf+C/ZQkzHKfm1cB\n" +
            "SiObqjDOgbEXWIODpt4v+aKJeYEEEBnJo6231JeHSr7tL3y0erTZkong69Oc503Q\n" +
            "/1PX0Ivt5MnWYbeMn4a6MvogGlliPC0B5BY4pLNwM2aWdkWflh6XLch1YC98JyPe\n" +
            "+wIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        CustomTokenEnhancer converter = new CustomTokenEnhancer();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .accessTokenConverter(tokenEnhancer())
                .tokenServices(tokenServices());
    }

    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        final CustomTokenServices tafTokenServices = new CustomTokenServices();
        tafTokenServices.setTokenStore(tokenStore());
        tafTokenServices.setTokenEnhancer(tokenEnhancer());
        return tafTokenServices;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("clientid")
                .secret(passwordEncoder.encode("secret"))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(86400);
    }
}

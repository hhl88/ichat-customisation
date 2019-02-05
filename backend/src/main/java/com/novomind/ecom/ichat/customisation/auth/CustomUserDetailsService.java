package com.novomind.ecom.ichat.customisation.auth;

import com.novomind.ecom.ichat.customisation.core.interfaces.dao.IChatUserDao;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger("UserDetailsService");

    @Autowired
    IChatUserDao userDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<IChatUser> user = userDAO.findIChatUserByEmail(s);
        logger.info("load user " + user.get());
        if (!user.isPresent()){
            throw new UsernameNotFoundException("username_not_found");
        }
        logger.info("found user " + new CustomUserDetails(user.get()));

        return new CustomUserDetails(user.get());
    }
}

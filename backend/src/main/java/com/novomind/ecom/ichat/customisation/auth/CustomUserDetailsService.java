package com.novomind.ecom.ichat.customisation.auth;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.novomind.ecom.ichat.customisation.core.interfaces.dao.IChatUserDao;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger("UserDetailsService");

    @Autowired
    IChatUserDao userDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<IChatUser> user = userDAO.findIChatUserByEmail(s);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("username_not_found");
        }
        return new CustomUserDetails(user.get());
    }
}
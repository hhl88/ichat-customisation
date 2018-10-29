package com.novomind.ecom.ichat.customisation.core.components;

import java.util.Optional;

import com.novomind.ecom.ichat.customisation.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.novomind.ecom.ichat.customisation.core.common.StringGenerator;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.IChatUserDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.IChatUserManagementService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

@Service("userManagementService")
public class IChatUserManagementServiceImpl implements IChatUserManagementService {
  
  Logger log = LoggerFactory.getLogger(getClass());

  
  @Autowired
  IChatUserDao userDAO;
  
  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public String register(IChatUser user) {
    String randomPassword = StringGenerator.generateRandomString();
    log.info("Password for " + user.getEmail() + " : " + randomPassword);
    user.setPassword(passwordEncoder.encode(randomPassword));
    return userDAO.insertIChatUser(user);
  }

  @Override
  public void updatePassword(IChatUser user, String oldPassword, String newPassword, String repeatedPassword) {
    if (!passwordEncoder.matches(oldPassword, user.getPassword())){
      throw new IllegalArgumentException("wrong_password");
  }
  if (newPassword.equals(repeatedPassword)) {
      user.setPassword(passwordEncoder.encode(newPassword));
      userDAO.updatePassword(user);
  } else {
      throw new IllegalArgumentException("retype_password_not_match");
  }
  }

  @Override
  public Optional<IChatUser> findIChatUserById(String id) {
    return userDAO.findIChatUserById(id);
  }

  @Override
  public Optional<IChatUser> findIChatUserByEmail(String email) {
    return userDAO.findIChatUserByEmail(email);
  }

  @Override
  public String findIdByEmail(String email) {
    return userDAO.findIChatUserByEmail(email).orElseThrow(() -> new NotFoundException("user_not_found")).getId();
  }

  @Override
  public boolean isEmailAvailable(String email) {
    return !findIChatUserByEmail(email).isPresent();
  }
  
}

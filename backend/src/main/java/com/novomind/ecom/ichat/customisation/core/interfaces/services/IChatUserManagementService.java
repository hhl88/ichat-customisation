package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.exceptions.UserNotFoundException;

import java.util.Optional;

public interface IChatUserManagementService {

  String register(IChatUser user);
  
  void updatePassword(IChatUser user, String oldPassword, String newPassword, String repeatedPassword);
  
  Optional<IChatUser> findIChatUserById(String id);
  
  Optional<IChatUser> findIChatUserByEmail(String email);

  String findIdByEmail(String email) throws UserNotFoundException;

  boolean isEmailAvailable(String email);
  
}

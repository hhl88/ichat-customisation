package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import java.util.Optional;

import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

public interface IChatUserManagementService {

  String register(IChatUser user);
  
  void updatePassword(IChatUser user, String oldPassword, String newPassword, String repeatedPassword);
  
  Optional<IChatUser> findIChatUserById(String id);
  
  Optional<IChatUser> findIChatUserByEmail(String email);

  String findIdByEmail(String email);

  boolean isEmailAvailable(String email);
  
}

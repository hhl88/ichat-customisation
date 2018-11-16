package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

import java.util.Optional;

public interface IChatUserDao {

  String insertIChatUser(IChatUser user);
  
  void updatePassword(IChatUser user);
  
  Optional<IChatUser> findIChatUserById(String id);
  
  Optional<IChatUser> findIChatUserByEmail(String email);
  
}

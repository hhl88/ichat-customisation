package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

public interface ChooseLayoutDao {
  
  void insert(IChatUser user, ChatLayout layout);
  
  void changeToAnother(IChatUser user, ChatLayout layout);
  
}

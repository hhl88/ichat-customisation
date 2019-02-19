package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

import java.util.Optional;

public interface ChooseChatFrontEndDao {

  String findChatFrontEndIdByUserId(String userId);
  
  void insert(ChatFrontEnd chatFrontEnd);
  
  void changeToAnother(ChatFrontEnd chatFrontEnd);

}

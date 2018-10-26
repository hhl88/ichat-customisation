package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import java.util.List;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

public interface ChatFrontEndDao {
  
  String insertChatFrontEnd(IChatUser user, ChatFrontEnd chatFrontEnd);
  
  void updateChatFrontEnd(ChatFrontEnd chatFrontEnd);
  
  ChatFrontEnd findChatFrontEndById(String id);
  
  List<ChatFrontEnd> findChatFrontEndByUserId(String userId);
  
}

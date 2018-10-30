package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import java.util.List;
import java.util.Optional;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

public interface ChatFrontEndDao {
  
  String insertChatFrontEnd(IChatUser user, ChatFrontEnd chatFrontEnd);
  
  void updateChatFrontEnd(String id, ChatFrontEnd chatFrontEnd);
  
  Optional <ChatFrontEnd> findChatFrontEndById(String id);
  
  List<ChatFrontEnd> findChatFrontEndByUserId(String userId);
  
}

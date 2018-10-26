package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import java.util.List;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

public interface ChatLayoutService {
  
  String insertNewChatLayout(IChatUser user, ChatLayout chatLayout);
  
  void updateInfo(ChatLayout chatLayout);
  
  ChatLayout findChatLayoutById(String id);
  
  List<ChatLayout> findChatLayoutByUserId(String userId);
  
}

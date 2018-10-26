package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import java.util.List;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

public interface ChatLayoutDao {
  
  String insertChatLayout(IChatUser user, ChatLayout chatLayout);

  void updateChatLayout(ChatLayout chatLayout);

  ChatLayout findChatLayoutById(String id);
  
  List<ChatLayout> findChatLayoutByUserId(String userId);

}

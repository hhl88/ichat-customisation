package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;

import java.util.List;
import java.util.Optional;

public interface ChatLayoutDao {

  String insertChatLayout(ChatLayout chatLayout);

  void updateChatLayout(ChatLayout chatLayout);

  Optional<ChatLayout> findChatLayoutById(String id);
  
  List<ChatLayout> findChatLayoutByUserId(String userId);

}

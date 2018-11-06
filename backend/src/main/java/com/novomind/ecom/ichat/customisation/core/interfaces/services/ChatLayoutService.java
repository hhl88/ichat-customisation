package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import java.util.List;
import java.util.Optional;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutUpdateDTO;

public interface ChatLayoutService {

  String insertNewChatLayout(IChatUser user, ChatLayoutCreateDTO dto);
  
  void updateInfo(ChatLayout chatLayout, ChatLayoutUpdateDTO dto);
  
  Optional<ChatLayout> findChatLayoutById(String id);
  
  List<ChatLayout> findChatLayoutByUserId(String userId);
  
}

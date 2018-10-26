package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import java.util.List;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

public interface ChatFrontEndManagementService {

  String insertChatFrontEnd(IChatUser user, ChatFrontEnd chatFrontEnd);
  
  void updateInfo(ChatFrontEnd chatFrontEnd);
  
  ChatFrontEnd findChatFrontEndById(String id);
  
  List<ChatFrontEnd> findChatFrontEndByUserId(String userId);
  
}

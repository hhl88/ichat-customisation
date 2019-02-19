package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

import java.util.Optional;

public interface ChooseLayoutService {
  
  void setAsDefault(ChatLayout layout);

  String findChatLayoutIdByUserId(String userId);

}

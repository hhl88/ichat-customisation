package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

import java.util.Optional;

public interface ChooseChatFrontEndService {

  void setAsDefault(ChatFrontEnd chatFrontEnd);

  String findChatFrontEndIdByUserId(String userId);
    
}

package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

public interface ChooseLayoutService {
  
  void chooseLayOut(IChatUser user, ChatLayout layout);
  
}

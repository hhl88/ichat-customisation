package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

public interface ChooseChatFrontEndService {

  void choose(IChatUser user, ChatFrontEnd chatFrontEnd);
    
}

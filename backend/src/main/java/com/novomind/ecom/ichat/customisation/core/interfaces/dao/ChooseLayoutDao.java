package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

import java.util.Optional;

public interface ChooseLayoutDao {

    void insert(ChatLayout layout);

    void changeToAnother(ChatLayout layout);

    String findChatLayoutIdByUserId(String userId);
    
}

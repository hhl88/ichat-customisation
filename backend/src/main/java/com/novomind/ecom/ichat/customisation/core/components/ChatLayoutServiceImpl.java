package com.novomind.ecom.ichat.customisation.core.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatLayoutDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatLayoutService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

@Service
public class ChatLayoutServiceImpl implements ChatLayoutService{
  
  @Autowired
  ChatLayoutDao chatLayoutDao;
  
  @Override
  public String insertNewChatLayout(IChatUser user, ChatLayout chatLayout) {
   return chatLayoutDao.insertChatLayout(user, chatLayout);
  }

  @Override
  public void updateInfo(ChatLayout chatLayout) {
    chatLayoutDao.updateChatLayout(chatLayout);
  }

  @Override
  public ChatLayout findChatLayoutById(String id) {
    return chatLayoutDao.findChatLayoutById(id);
  }

  @Override
  public List<ChatLayout> findChatLayoutByUserId(String userId) {
    return chatLayoutDao.findChatLayoutByUserId(userId);
  }

}

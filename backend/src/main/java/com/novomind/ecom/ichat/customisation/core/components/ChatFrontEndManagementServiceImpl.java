package com.novomind.ecom.ichat.customisation.core.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatFrontEndDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatFrontEndManagementService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

@Service
public class ChatFrontEndManagementServiceImpl implements ChatFrontEndManagementService {
  
  @Autowired
  ChatFrontEndDao chatFrontEndDao;
  
  @Override
  public String insertChatFrontEnd(IChatUser user, ChatFrontEnd chatFrontEnd) {
    String id = chatFrontEndDao.insertChatFrontEnd(user, chatFrontEnd);
    // TODO
    return id;
  }

  @Override
  public void updateInfo(ChatFrontEnd chatFrontEnd) {
    chatFrontEndDao.updateChatFrontEnd(chatFrontEnd); 
  }

  @Override
  public ChatFrontEnd findChatFrontEndById(String id) {
    return chatFrontEndDao.findChatFrontEndById(id);
  }

  @Override
  public List<ChatFrontEnd> findChatFrontEndByUserId(String userId) {
    return chatFrontEndDao.findChatFrontEndByUserId(userId);
  }

}

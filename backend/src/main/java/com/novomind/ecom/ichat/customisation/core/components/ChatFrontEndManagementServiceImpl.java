package com.novomind.ecom.ichat.customisation.core.components;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatFrontEndDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatFrontEndManagementService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndUpdateDTO;

@Service("chatFrontEndManagementService")
public class ChatFrontEndManagementServiceImpl implements ChatFrontEndManagementService {
  
  @Autowired
  ChatFrontEndDao chatFrontEndDao;
  
  @Override
  public String insertChatFrontEnd(IChatUser user,FrontEndCreateDTO dto) {
    ChatFrontEnd chatFrontEnd = ChatFrontEnd.of(dto);
    String id = chatFrontEndDao.insertChatFrontEnd(user, chatFrontEnd);
    // TODO
    return id;
  }

  @Override
  public void updateInfo(String id, FrontEndUpdateDTO dto) {
    chatFrontEndDao.updateChatFrontEnd(id, ChatFrontEnd.of(dto)); 
  }

  @Override
  public Optional<ChatFrontEnd> findChatFrontEndById(String id) {
    return chatFrontEndDao.findChatFrontEndById(id);
  }

  @Override
  public List<ChatFrontEnd> findChatFrontEndByUserId(String userId) {
    return chatFrontEndDao.findChatFrontEndByUserId(userId);
  }

}

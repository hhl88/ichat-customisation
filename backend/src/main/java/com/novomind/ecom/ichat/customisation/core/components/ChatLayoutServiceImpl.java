package com.novomind.ecom.ichat.customisation.core.components;

import java.util.List;
import java.util.Optional;

import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatLayoutDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatLayoutService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

@Service
public class ChatLayoutServiceImpl implements ChatLayoutService {

    @Autowired
    ChatLayoutDao chatLayoutDao;

    @Override
    public String insertNewChatLayout(IChatUser user, ChatLayoutCreateDTO dto) {
        ChatLayout chatLayout = ChatLayout.of(dto);
        chatLayout.setUserId(user.getId());
        return chatLayoutDao.insertChatLayout(chatLayout);
    }

    @Override
    public void updateInfo(ChatLayout chatLayout, ChatLayoutUpdateDTO dto) {

    }

    @Override
    public Optional<ChatLayout> findChatLayoutById(String id) {
        return chatLayoutDao.findChatLayoutById(id);
    }

    @Override
    public List<ChatLayout> findChatLayoutByUserId(String userId) {
        return chatLayoutDao.findChatLayoutByUserId(userId);
    }

}

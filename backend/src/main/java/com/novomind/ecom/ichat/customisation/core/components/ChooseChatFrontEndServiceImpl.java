package com.novomind.ecom.ichat.customisation.core.components;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChooseChatFrontEndDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChooseChatFrontEndService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChooseChatFrontEndServiceImpl implements ChooseChatFrontEndService {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ChooseChatFrontEndDao chooseChatFrontEndDao;

    @Override
    public void setAsDefault(ChatFrontEnd chatFrontEnd) {
        String storedChatFrontEndId = findChatFrontEndIdByUserId(chatFrontEnd.getUserId());
        log.info("setAsDefault " + storedChatFrontEndId);
        if (storedChatFrontEndId != null) {
            chooseChatFrontEndDao.changeToAnother(chatFrontEnd);
        } else {
            chooseChatFrontEndDao.insert(chatFrontEnd);
        }
    }

    @Override
    public String findChatFrontEndIdByUserId(String userId) {
        return chooseChatFrontEndDao.findChatFrontEndIdByUserId(userId);
    }

}

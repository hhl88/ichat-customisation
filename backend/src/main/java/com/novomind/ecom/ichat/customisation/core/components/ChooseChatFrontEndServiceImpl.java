package com.novomind.ecom.ichat.customisation.core.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChooseChatFrontEndDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChooseChatFrontEndService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

@Service
public class ChooseChatFrontEndServiceImpl implements ChooseChatFrontEndService {

    @Autowired
    ChooseChatFrontEndDao chooseChatFrontEndDao;

    @Override
    public void choose(IChatUser user, ChatFrontEnd chatFrontEnd) {
        if (chatFrontEnd.getId() == null)
            chooseChatFrontEndDao.insert(user, chatFrontEnd);
        else
            chooseChatFrontEndDao.changeToAnother(user, chatFrontEnd);
    }

}

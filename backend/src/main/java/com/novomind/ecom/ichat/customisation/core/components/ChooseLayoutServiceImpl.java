package com.novomind.ecom.ichat.customisation.core.components;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChooseLayoutDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChooseLayoutService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChooseLayoutServiceImpl implements ChooseLayoutService {

    @Autowired
    ChooseLayoutDao chooseLayoutDao;



    @Override
    public void setAsDefault(ChatLayout chatLayout) {
        String storedChatLayoutId = findChatLayoutIdByUserId(chatLayout.getUserId());
        if (storedChatLayoutId != null) {
            chooseLayoutDao.changeToAnother(chatLayout);
        } else {
            chooseLayoutDao.insert(chatLayout);
        }
    }

    @Override
    public String findChatLayoutIdByUserId(String userId) {
        return chooseLayoutDao.findChatLayoutIdByUserId(userId);
    }


}

package com.novomind.ecom.ichat.customisation.core.components;

import org.springframework.beans.factory.annotation.Autowired;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChooseLayoutDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChooseLayoutService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

public class ChooseLayoutServiceImpl implements ChooseLayoutService{

  @Autowired
  ChooseLayoutDao chooseLayoutDao;
  
  @Override
  public void chooseLayOut(IChatUser user, ChatLayout layout) {
    if(layout.getId() == null) 
      chooseLayoutDao.insert(user, layout);
    else
      chooseLayoutDao.changeToAnother(user, layout);
  }

}

package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.chat.setting.ChatFrontEndSetting;

public interface ChatFrontEndSettingDao {
    
  String insertNewSetting(ChatFrontEnd chatFrontEnd, ChatFrontEndSetting setting);
  
  void updateSetting(ChatFrontEnd chatFrontEnd, ChatFrontEndSetting setting);
  
  ChatFrontEndSetting findSettingByInfoChatFrontEndId(String chatFrontEndId);
  

}

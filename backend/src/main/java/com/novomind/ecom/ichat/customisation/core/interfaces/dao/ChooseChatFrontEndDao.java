package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

public interface ChooseChatFrontEndDao {
  
  //  void insert(IChatUser user, ConnectionType type, String chatFrontEndId, String serverId, String urlPath, String demandInfoId);
  //  
  //  void changeToAnother(IChatUser user, ConnectionType type, String chatFrontEndId, String serverId, String urlPath, String demandInfoId);

  
  void insert(IChatUser user, ChatFrontEnd chatFrontEnd);
  
  void changeToAnother(IChatUser user, ChatFrontEnd chatFrontEnd);
}

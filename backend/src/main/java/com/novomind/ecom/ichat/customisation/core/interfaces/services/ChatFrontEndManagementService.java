package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface ChatFrontEndManagementService {

    String insertChatFrontEnd(IChatUser user, FrontEndCreateDTO dto);

    void update(ChatFrontEnd chatFrontEnd, FrontEndUpdateDTO dto);

    Optional<ChatFrontEnd> findChatFrontEndById(String id);

    List<ChatFrontEnd> findChatFrontEndByUserId(String userId);

}

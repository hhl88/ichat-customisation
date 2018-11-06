package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import java.util.List;
import java.util.Optional;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndSettingDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndUpdateDTO;
import com.novomind.ecom.ichat.customisation.exceptions.ChatFrontEndNotFoundException;

public interface ChatFrontEndManagementService {

    String insertChatFrontEnd(IChatUser user, FrontEndCreateDTO dto);

    void update(ChatFrontEnd chatFrontEnd, FrontEndUpdateDTO dto);

    Optional<ChatFrontEnd> findChatFrontEndById(String id);

    List<FrontEndDTO> findChatFrontEndByUserId(String userId);

}

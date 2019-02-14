package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ChatLayoutService {

    ChatLayout insertNewChatLayout(IChatUser user, ChatLayoutCreateDTO dto, MultipartFile logoImg, MultipartFile backgroundImg) throws IOException;

    ChatLayout updateInfo(ChatLayout chatLayout, ChatLayoutUpdateDTO dto, MultipartFile logoImg, MultipartFile backgroundImg) throws IOException;

    Optional<ChatLayout> findChatLayoutById(String id);

    List<ChatLayout> findChatLayoutByUserId(String userId);


}

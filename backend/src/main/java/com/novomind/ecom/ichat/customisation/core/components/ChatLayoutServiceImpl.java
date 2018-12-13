package com.novomind.ecom.ichat.customisation.core.components;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatLayoutDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatLayoutService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.StoreService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ChatLayoutServiceImpl implements ChatLayoutService {

    @Autowired
    ChatLayoutDao chatLayoutDao;

    @Autowired
    StoreService storeService;

    private final String LOGO_DEFAULT = "no-logo.jpg";
    private final String BACKGROUND_DEFAULT = "no-background-img.jpg";


    @Override
    public String insertNewChatLayout(IChatUser user, ChatLayoutCreateDTO dto) {
        ChatLayout chatLayout = ChatLayout.of(dto);
        saveImages(chatLayout, dto);
        chatLayout.setUserId(user.getId());
        return chatLayoutDao.insertChatLayout(chatLayout);
    }

    @Override
    public void updateInfo(ChatLayout chatLayout, ChatLayoutUpdateDTO dto) {
        ChatLayout newChatLayout = ChatLayout.of(dto);
        saveImages(newChatLayout, dto);
        newChatLayout.setId(chatLayout.getId());
        chatLayoutDao.updateChatLayout(newChatLayout);
    }

    @Override
    public Optional<ChatLayout> findChatLayoutById(String id) {
        return chatLayoutDao.findChatLayoutById(id);
    }

    @Override
    public List<ChatLayout> findChatLayoutByUserId(String userId) {
        return chatLayoutDao.findChatLayoutByUserId(userId);
    }

    public Resource getLogo(ChatLayout chatLayout) {
        String logo = chatLayout.getLogo();
        if (logo.equalsIgnoreCase(LOGO_DEFAULT)) return null;
        return storeService.loadFile(chatLayout.getLogo());
    }

    public Resource getBackgroundImage(ChatLayout chatLayout) {
        String backgroundImg = chatLayout.getBackgroundImg();
        if (backgroundImg.equalsIgnoreCase(BACKGROUND_DEFAULT)) return null;
        return storeService.loadFile(backgroundImg);
    }


    private void saveImages(ChatLayout chatLayout, ChatLayoutUpdateDTO dto) {
        String logo = LOGO_DEFAULT;
        String backgroundImg = BACKGROUND_DEFAULT;

        if (!dto.getLogo().isEmpty()) {
            logo = storeService.store(dto.getLogo());
        }
        if (!dto.getBackgroundImg().isEmpty()) {
            backgroundImg = storeService.store(dto.getBackgroundImg());
        }
        chatLayout.setLogo(logo);
        chatLayout.setBackgroundImg(backgroundImg);
    }

    private void saveImages(ChatLayout chatLayout, ChatLayoutCreateDTO dto) {
        String logo = LOGO_DEFAULT;
        String backgroundImg = BACKGROUND_DEFAULT;

        if (!dto.getLogo().isEmpty()) {
            logo = storeService.store(dto.getLogo());
        }
        if (!dto.getBackgroundImg().isEmpty()) {
            backgroundImg = storeService.store(dto.getBackgroundImg());
        }
        chatLayout.setLogo(logo);
        chatLayout.setBackgroundImg(backgroundImg);
    }

}

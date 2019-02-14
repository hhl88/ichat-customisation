package com.novomind.ecom.ichat.customisation.core.components;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatLayoutDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.ChatLayoutService;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.StoreService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutUpdateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ChatLayoutServiceImpl implements ChatLayoutService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ChatLayoutDao chatLayoutDao;

    @Autowired
    StoreService storeService;


    @Override
    @Transactional(rollbackFor = IOException.class)
    public ChatLayout insertNewChatLayout(IChatUser user, ChatLayoutCreateDTO dto, MultipartFile logoImg, MultipartFile backgroundImg) throws IOException {
        ChatLayout chatLayout = ChatLayout.of(dto);
        addImages(chatLayout, logoImg, backgroundImg);
        chatLayout.setUserId(user.getId());
        String id = chatLayoutDao.insertChatLayout(chatLayout);
        chatLayout.setId(id);
        return chatLayout;
    }

    @Override
    @Transactional(rollbackFor = IOException.class)
    public ChatLayout updateInfo(ChatLayout chatLayout, ChatLayoutUpdateDTO dto, MultipartFile logoImg, MultipartFile backgroundImg) throws IOException {
        ChatLayout newChatLayout = ChatLayout.of(dto);
        newChatLayout.setLogo(chatLayout.getLogo());
        newChatLayout.setBackgroundImg(chatLayout.getBackgroundImg());

        addImages(newChatLayout, logoImg, backgroundImg);
        newChatLayout.setId(chatLayout.getId());
        log.info("newCHatlayout " + newChatLayout);
        chatLayoutDao.updateChatLayout(newChatLayout);
        return newChatLayout;
    }

    @Override
    public Optional<ChatLayout> findChatLayoutById(String id) {
        return chatLayoutDao.findChatLayoutById(id);
    }

    @Override
    public List<ChatLayout> findChatLayoutByUserId(String userId) {
        return chatLayoutDao.findChatLayoutByUserId(userId);
    }


    private String saveImages(MultipartFile image) throws IOException {
        String imageStoredName = null;

        if (image != null && !image.isEmpty()) {
            log.info("image " + image.getOriginalFilename() + " - - - " + image.getSize());
            imageStoredName = storeService.store(image);
        }
        return imageStoredName;
    }

    private void addImages(ChatLayout chatLayout, MultipartFile logoImg, MultipartFile backgroundImg) throws IOException {

        String newLogoStoredName = saveImages(logoImg);
        if (newLogoStoredName != null) {
            log.info("new logo " + newLogoStoredName);

            if (chatLayout.getLogo() != null) {
                log.info("remove logo");
                storeService.removeFile(chatLayout.getLogo());
            }
            chatLayout.setLogo(newLogoStoredName);
            log.info("set new logo " + chatLayout);
        }

        String newBackgroundImgStoredName = saveImages(backgroundImg);
        if (newBackgroundImgStoredName != null) {
            log.info("set background " + chatLayout);

            if (chatLayout.getBackgroundImg() != null) {
                log.info("remove background");
                storeService.removeFile(chatLayout.getBackgroundImg());
            }
            chatLayout.setBackgroundImg(newBackgroundImgStoredName);
            log.info("set new background " + chatLayout);

        }
    }

}

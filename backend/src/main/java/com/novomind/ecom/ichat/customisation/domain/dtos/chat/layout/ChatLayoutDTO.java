package com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.domain.datatypes.*;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontDTO;

public class ChatLayoutDTO {

    private String id;
    private String name;
    private String displayType;
    private String textInputType;
    private String buttonType;
    private String logo;
    private String backgroundType;
    private String backgroundImg;
    private FontDTO font;

    private ChatLayoutDTO() {
    }

    public ChatLayoutDTO(String id, String name, String displayType, String textInputType, String buttonType, String logo, String backgroundType, String backgroundImg, FontDTO font) {
        this.id = id;
        this.name = name;
        this.displayType = displayType;
        this.textInputType = textInputType;
        this.buttonType = buttonType;
        this.logo = logo;
        this.backgroundType = backgroundType;
        this.backgroundImg = backgroundImg;
        this.font = font;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayType() {
        return displayType;
    }

    public String getTextInputType() {
        return textInputType;
    }

    public String getButtonType() {
        return buttonType;
    }

    public String getLogo() {
        return logo;
    }

    public String getBackgroundType() {
        return backgroundType;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public FontDTO getFont() {
        return font;
    }

    public static ChatLayoutDTO of(ChatLayout chatLayout) {
        return new ChatLayoutDTO(chatLayout.getId(),
                chatLayout.getName(),
                String.valueOf(chatLayout.getDisplayType().ordinal()),
                String.valueOf(chatLayout.getTextInputType().ordinal()),
                String.valueOf(chatLayout.getButtonType().ordinal()),
                chatLayout.getLogo(),
                String.valueOf(chatLayout.getBackgroundType().ordinal()),
                chatLayout.getBackgroundImg(),
                FontDTO.of(chatLayout.getFont()));
    }
}

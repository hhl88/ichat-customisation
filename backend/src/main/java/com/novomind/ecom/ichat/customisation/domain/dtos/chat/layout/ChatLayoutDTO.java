package com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.domain.datatypes.*;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontDTO;

public class ChatLayoutDTO {

    private String id;
    private String name;
    private int displayType;
    private int textInputType;
    private int buttonType;
    private String logo;
    private int backgroundType;
    private String backgroundImg;
    private FontDTO font;

    private ChatLayoutDTO() {
    }

    public ChatLayoutDTO(String id, String name, int displayType, int textInputType, int buttonType, String logo, int backgroundType, String backgroundImg, FontDTO font) {
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

    public int getDisplayType() {
        return displayType;
    }

    public int getTextInputType() {
        return textInputType;
    }

    public int getButtonType() {
        return buttonType;
    }

    public String getLogo() {
        return logo;
    }

    public int getBackgroundType() {
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
                chatLayout.getDisplayType(),
                chatLayout.getTextInputType(),
                chatLayout.getButtonType(),
                chatLayout.getLogo(),
                chatLayout.getBackgroundType(),
                chatLayout.getBackgroundImg(),
                FontDTO.of(chatLayout.getFont()));
    }
}

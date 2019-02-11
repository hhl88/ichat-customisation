package com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.domain.datatypes.*;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontDTO;

public class ChatLayoutDTO {

    private String id;
    private String name;
    private LayoutDisplay displayType;
    private TextAreaDisplay textInputType;
    private ButtonDisplay buttonType;
    private String logo;
    private BackgroundDisplay backgroundType;
    private String backgroundImg;
    private FontDTO font;

    private ChatLayoutDTO() {
    }

    public ChatLayoutDTO(String id, String name, LayoutDisplay displayType, TextAreaDisplay textInputType, ButtonDisplay buttonType, String logo, BackgroundDisplay backgroundType, String backgroundImg, FontDTO font) {
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

    public LayoutDisplay getDisplayType() {
        return displayType;
    }

    public TextAreaDisplay getTextInputType() {
        return textInputType;
    }

    public ButtonDisplay getButtonType() {
        return buttonType;
    }

    public String getLogo() {
        return logo;
    }

    public BackgroundDisplay getBackgroundType() {
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

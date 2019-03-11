package com.novomind.ecom.ichat.customisation.core.chat.layout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Bubble;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Font;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutUpdateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontDTO;

import java.io.Serializable;
import java.util.Map;


public class ChatLayout implements Serializable {

    private String id;
    @JsonIgnore
    private String userId;
    private String name;
    private int displayType;
    private int textInputType;
    private int buttonType;
    private String logo;
    private String backgroundImg;
    private int backgroundType;
    private Font font;
    private Map<String, Bubble> bubbleStyle;


    public static ChatLayout of(ChatLayoutCreateDTO dto) {
        FontDTO fontDTO = dto.getFont();
        return new ChatLayout()
                .setName(dto.getName())
                .setDisplayType(dto.getDisplayType().ordinal())
                .setTextInputType(dto.getTextInputType().ordinal())
                .setButtonType(dto.getButtonType().ordinal())
                .setBackgroundType(dto.getBackgroundType().ordinal())
                .setFont(Font.of(fontDTO.getFontFamily(), fontDTO.getFontSize(), fontDTO.getFontStyles()))
                .setBubbleStyle(dto.getBubbleStyle());
    }

    public static ChatLayout of(ChatLayoutUpdateDTO dto) {
        FontDTO fontDTO = dto.getFont();
        return new ChatLayout()
                .setId(dto.getId())
                .setName(dto.getName())
                .setDisplayType(dto.getDisplayType().ordinal())
                .setTextInputType(dto.getTextInputType().ordinal())
                .setButtonType(dto.getButtonType().ordinal())
                .setBackgroundType(dto.getBackgroundType().ordinal())
                .setFont(Font.of(fontDTO.getFontFamily(), fontDTO.getFontSize(), fontDTO.getFontStyles()))
                .setBubbleStyle(dto.getBubbleStyle());
    }

    private ChatLayout() {
    }

    public ChatLayout(String id, String userId, String name, int displayType, int textInputType, int buttonType, String logo, String backgroundImg, int backgroundType, Font font, Map<String, Bubble> bubbleStyle) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.displayType = displayType;
        this.textInputType = textInputType;
        this.buttonType = buttonType;
        this.logo = logo;
        this.backgroundImg = backgroundImg;
        this.backgroundType = backgroundType;
        this.font = font;
        this.bubbleStyle = bubbleStyle;
    }

    public String getId() {
        return id;
    }

    public ChatLayout setId(String id) {
        this.id = id;
        return this;
    }

    @JsonIgnore
    public String getUserId() {
        return userId;
    }

    public ChatLayout setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ChatLayout setName(String name) {
        this.name = name;
        return this;
    }

    public int getDisplayType() {
        return displayType;
    }

    public ChatLayout setDisplayType(int displayType) {
        this.displayType = displayType;
        return this;
    }

    public int getTextInputType() {
        return textInputType;
    }

    public ChatLayout setTextInputType(int textInputType) {
        this.textInputType = textInputType;
        return this;
    }

    public int getButtonType() {
        return buttonType;
    }

    public ChatLayout setButtonType(int buttonType) {
        this.buttonType = buttonType;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public ChatLayout setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public ChatLayout setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
        return this;
    }

    public int getBackgroundType() {
        return backgroundType;
    }

    public ChatLayout setBackgroundType(int backgroundType) {
        this.backgroundType = backgroundType;
        return this;
    }

    public Font getFont() {
        return font;
    }

    public ChatLayout setFont(Font font) {
        this.font = font;
        return this;
    }

    public Map<String, Bubble> getBubbleStyle() {
        return bubbleStyle;
    }

    public ChatLayout setBubbleStyle(Map<String, Bubble> bubbleStyle) {
        this.bubbleStyle = bubbleStyle;
        return this;
    }
}

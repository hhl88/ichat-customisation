package com.novomind.ecom.ichat.customisation.core.chat.layout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novomind.ecom.ichat.customisation.domain.datatypes.*;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutUpdateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontDTO;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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


    public static ChatLayout of(ChatLayoutCreateDTO dto) {
        FontDTO fontDTO = dto.getFont();
        return ChatLayout.builder()
                .name(dto.getName())
                .displayType(dto.getDisplayType().ordinal())
                .textInputType(dto.getTextInputType().ordinal())
                .buttonType(dto.getButtonType().ordinal())
                .backgroundType(dto.getBackgroundType().ordinal())
                .font(Font.of(fontDTO.getFontFamily(), fontDTO.getFontSize(), fontDTO.getFontStyles()))
                .build();
    }

    public static ChatLayout of(ChatLayoutUpdateDTO dto) {
        FontDTO fontDTO = dto.getFont();
        return ChatLayout.builder()
                .name(dto.getName())
                .displayType(dto.getDisplayType().ordinal())
                .textInputType(dto.getTextInputType().ordinal())
                .buttonType(dto.getButtonType().ordinal())
                .backgroundType(dto.getBackgroundType().ordinal())
                .backgroundType(dto.getBackgroundType().ordinal())
                .font(Font.of(fontDTO.getFontFamily(), fontDTO.getFontSize(), fontDTO.getFontStyles()))
                .build();
    }

}

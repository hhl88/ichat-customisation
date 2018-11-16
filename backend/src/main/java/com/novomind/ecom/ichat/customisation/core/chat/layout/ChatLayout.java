package com.novomind.ecom.ichat.customisation.core.chat.layout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novomind.ecom.ichat.customisation.domain.datatypes.*;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutUpdateDTO;
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
    private LayoutDisplay displayType;
    private TextAreaDisplay textInputType;
    private ButtonDisplay buttonType;
    private String logo;
    private String backgroundImg;
    private BackgroundDisplay backgroundType;
    private Font font;


    public static ChatLayout of(ChatLayoutCreateDTO dto) {
        return ChatLayout.builder()
                .name(dto.getName())
                .displayType(dto.getDisplayType())
                .textInputType(dto.getTextInputType())
                .buttonType(dto.getButtonType())
                .logo(dto.getLogo())
                .backgroundImg(dto.getBackgroundImg())
                .backgroundType(dto.getBackgroundType())
                .font(dto.getFont())
                .build();
    }

    public static ChatLayout of(ChatLayoutUpdateDTO dto) {
        return ChatLayout.builder()
                .name(dto.getName())
                .displayType(dto.getDisplayType())
                .textInputType(dto.getTextInputType())
                .buttonType(dto.getButtonType())
                .logo(dto.getLogo())
                .backgroundImg(dto.getBackgroundImg())
                .backgroundType(dto.getBackgroundType())
                .font(dto.getFont())
                .build();
    }

}

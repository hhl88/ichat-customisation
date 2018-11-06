package com.novomind.ecom.ichat.customisation.core.chat.layout;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novomind.ecom.ichat.customisation.domain.datatypes.*;

import com.novomind.ecom.ichat.customisation.domain.dtos.chat.layout.ChatLayoutCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.font.FontDTO;
import lombok.*;

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
                .displayType(dto.getLayoutDisplay())
                .textInputType(dto.getTextAreaDisplay())
                .buttonType(dto.getButtonDisplay())
                .logo(dto.getLogoImage())
                .backgroundImg(dto.getBackgroundImage())
                .backgroundType(dto.getBackgroundDisplay())
                .font(dto.getFont())
                .build();
    }

}

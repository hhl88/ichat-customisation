package com.novomind.ecom.ichat.customisation.core.chat.frontend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Connection;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class ChatFrontEnd implements Serializable {

    private String id;
    @JsonIgnore
    private String userId;
    private String name;

    private String iAgentServerId;

    private String cloudId;

    private String urlPath;

    private String demandInfoId;

    private Connection connectionType;

    public static ChatFrontEnd of(FrontEndCreateDTO dto) {

        return ChatFrontEnd.builder()
                .name(dto.getName())
                .connectionType(dto.getConnectionType())
                .urlPath(dto.getUrlPath())
                .build();
    }

    public static ChatFrontEnd of(FrontEndUpdateDTO dto) {

        return ChatFrontEnd.builder()
                .name(dto.getName())
                .connectionType(dto.getConnectionType())
                .urlPath(dto.getUrlPath())
                .build();
    }

}

package com.novomind.ecom.ichat.customisation.core.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndDTO;

public class Settings {
    private FrontEndDTO chatFrontEnd = null;
    private ChatLayout chatLayout = null;

    public Settings() {

    }

    @JsonProperty("frontend")
    public FrontEndDTO getChatFrontEnd() {
        return chatFrontEnd;
    }

    public void setChatFrontEnd(FrontEndDTO chatFrontEnd) {
        this.chatFrontEnd = chatFrontEnd;
    }

    @JsonProperty("layout")
    public ChatLayout getChatLayout() {
        return chatLayout;
    }

    public void setChatLayout(ChatLayout chatLayout) {
        this.chatLayout = chatLayout;
    }
}

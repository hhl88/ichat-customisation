package com.novomind.ecom.ichat.customisation.core.utils;

import com.novomind.ecom.ichat.customisation.core.chat.frontend.ChatFrontEnd;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndDTO;

public final class IChatUtils {


    private IChatUtils() { }

    public static FrontEndDTO convertToFrontEndDTO(ChatFrontEnd chatFrontEnd, IAgentServer iAgentServer, DemandInfo demandInfo) {
        return new FrontEndDTO(chatFrontEnd.getId(), chatFrontEnd.getName(), iAgentServer, chatFrontEnd.getUrlPath(), demandInfo, chatFrontEnd.getConnectionType());
    }

}

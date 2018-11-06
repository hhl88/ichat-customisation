package com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend;

import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Connection;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FrontEndSettingDTO {

    private String id;
    private IAgentServer iAgentServer;
    private String urlPath;
    private DemandInfo demandInfo;
    private Connection connectionType;

}

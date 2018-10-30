package com.novomind.ecom.ichat.customisation.core.chat.setting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatFrontEndSetting {

  private String iAgentServerId;

  private String cloudId;

  private String urlPath;

  private String demandInfoId;


}

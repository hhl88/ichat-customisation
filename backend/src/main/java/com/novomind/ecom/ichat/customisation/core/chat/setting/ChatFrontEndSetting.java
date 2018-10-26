package com.novomind.ecom.ichat.customisation.core.chat.setting;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ChatFrontEndSetting {

  private String iAgentServerId;

  @JsonIgnore
  private String cloudId;

  private String urlPath;

  private String demandInfoId;

  public ChatFrontEndSetting() {
  }

}

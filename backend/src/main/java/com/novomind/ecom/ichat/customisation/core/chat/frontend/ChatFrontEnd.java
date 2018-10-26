package com.novomind.ecom.ichat.customisation.core.chat.frontend;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class ChatFrontEnd implements Serializable {
  
  private String id;
  private String name;
  private String connectionType;
  
  public ChatFrontEnd() {}
  
}

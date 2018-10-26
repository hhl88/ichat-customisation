package com.novomind.ecom.ichat.customisation.core.server.iagent;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class IAgentServer implements Serializable{
  
  private String id;
  private String address;
  private String userAPI;
  
  @JsonIgnore
  private String password;
  private String clientId;
  private String secret;
  
  public IAgentServer () {}
  
}

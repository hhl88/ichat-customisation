package com.novomind.ecom.ichat.customisation.core.server.iagent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@SuppressWarnings("serial")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IAgentServer implements Serializable{
  
  private String id;
  private String address;
  private String userAPI;
  
  @JsonIgnore
  private String password;
  private String clientId;
  private String secret;
  
  public static IAgentServer of(IAgentServerCreateDTO dto) {
    return IAgentServer.builder()
        .address(dto.getAddress())
        .userAPI(dto.getUserAPI())
        .password(dto.getPassword())
        .clientId(dto.getClientId())
        .secret(dto.getSecret())
        .build();
  }
  
  public static IAgentServer of(IAgentServerUpdateDTO dto) {
    return IAgentServer.builder()
        .address(dto.getAddress())
        .userAPI(dto.getUserAPI())
        .password(dto.getPassword())
        .clientId(dto.getClientId())
        .secret(dto.getSecret())
        .build();
  }
  
}

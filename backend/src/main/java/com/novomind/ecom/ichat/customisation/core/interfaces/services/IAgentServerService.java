package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerUpdateDTO;

public interface IAgentServerService {
  
  String addNewIAgentServer(IAgentServerCreateDTO dto);
  
  void updateInfo(String id, IAgentServerUpdateDTO server);
  
  IAgentServer findIAgentServerById(String id);
  
  String findServerIdByInfo(IAgentServer server);
  
}

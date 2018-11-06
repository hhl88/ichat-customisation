package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerUpdateDTO;

import java.util.Optional;

public interface IAgentServerService {

  String addNewIAgentServer(IAgentServer iAgentServer);
  
  void updateInfo(IAgentServer server);
  
  Optional<IAgentServer> findIAgentServerById(String id);

}

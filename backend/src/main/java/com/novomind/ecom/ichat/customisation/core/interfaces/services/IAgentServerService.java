package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;

import java.util.Optional;

public interface IAgentServerService {

  String addNewIAgentServer(IAgentServer iAgentServer);
  
  void updateInfo(IAgentServer server);
  
  Optional<IAgentServer> findIAgentServerById(String id);

}

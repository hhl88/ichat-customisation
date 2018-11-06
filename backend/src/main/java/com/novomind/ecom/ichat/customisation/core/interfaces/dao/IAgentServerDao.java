package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;

import java.util.Optional;

public interface IAgentServerDao {

  String insertIAgentServer(IAgentServer server);
  
  void updateIAgentServer(IAgentServer server);
  
  Optional<IAgentServer> findIAgentServerById(String id);

}

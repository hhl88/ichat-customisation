package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;

public interface IAgentServerService {
  
  String addNewIAgentServer(IAgentServer server);
  
  void updateInfo(IAgentServer server);
  
  IAgentServer findIAgentServerById(String id);
  
  String findServerIdByInfo(IAgentServer server);
  
}

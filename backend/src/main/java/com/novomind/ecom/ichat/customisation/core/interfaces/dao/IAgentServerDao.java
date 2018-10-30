package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;

public interface IAgentServerDao {
  
  String insertIAgentServer(IAgentServer server);
  
  void updateIAgentServer(String id, IAgentServer server);
  
  IAgentServer findIAgentServerById(String id);
  
  String findIdByInfo(IAgentServer server);
  
}

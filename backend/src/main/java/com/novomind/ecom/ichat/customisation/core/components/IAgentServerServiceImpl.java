package com.novomind.ecom.ichat.customisation.core.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.novomind.ecom.ichat.customisation.core.interfaces.dao.IAgentServerDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.IAgentServerService;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;

@Service
public class IAgentServerServiceImpl implements IAgentServerService {
  @Autowired
  IAgentServerDao serverDao;
  
  @Autowired
  PasswordEncoder passwordEncoder;
  
  @Override
  public String addNewIAgentServer(IAgentServer server) {
    String id = serverDao.findIdByInfo(server);
    if(id == null) {
      return serverDao.insertIAgentServer(server);
    }
    return id; 
  }

  @Override
  public void updateInfo(IAgentServer server) {
    serverDao.updateIAgentServer(server);
  }

  @Override
  public IAgentServer findIAgentServerById(String id) {
    return serverDao.findIAgentServerById(id);
  }

  @Override
  public String findServerIdByInfo(IAgentServer server) {
    return serverDao.findIdByInfo(server);
  }

}

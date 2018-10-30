package com.novomind.ecom.ichat.customisation.core.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.novomind.ecom.ichat.customisation.core.interfaces.dao.IAgentServerDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.IAgentServerService;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerUpdateDTO;

@Service
public class IAgentServerServiceImpl implements IAgentServerService {
  @Autowired
  IAgentServerDao serverDao;
  
  @Autowired
  PasswordEncoder passwordEncoder;
  
  @Override
  public String addNewIAgentServer(IAgentServerCreateDTO dto) {
   IAgentServer iAgentServer = IAgentServer.of(dto);
     iAgentServer.setPassword(passwordEncoder.encode(iAgentServer.getPassword()));
    String id = serverDao.findIdByInfo(iAgentServer);
    if(id == null) {
      return serverDao.insertIAgentServer(iAgentServer);
    }
    return id; 
  }

  @Override
  public void updateInfo(String id, IAgentServerUpdateDTO dto) {
    serverDao.updateIAgentServer(id, IAgentServer.of(dto));
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

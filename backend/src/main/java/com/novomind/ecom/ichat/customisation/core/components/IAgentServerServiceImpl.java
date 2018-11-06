package com.novomind.ecom.ichat.customisation.core.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.novomind.ecom.ichat.customisation.core.interfaces.dao.IAgentServerDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.IAgentServerService;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerUpdateDTO;

import java.util.Optional;

@Service
public class IAgentServerServiceImpl implements IAgentServerService {
    @Autowired
    IAgentServerDao serverDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String addNewIAgentServer(IAgentServer iAgentServer) {
        iAgentServer.setPassword(passwordEncoder.encode(iAgentServer.getPassword()));
        return serverDao.insertIAgentServer(iAgentServer);
    }

    @Override
    public void updateInfo(IAgentServer iAgentServer) {
        if(iAgentServer.getPassword() != null)
            iAgentServer.setPassword(passwordEncoder.encode(iAgentServer.getPassword()));
        serverDao.updateIAgentServer(iAgentServer);
    }

    @Override
    public Optional<IAgentServer> findIAgentServerById(String id) {
        return serverDao.findIAgentServerById(id);
    }


}

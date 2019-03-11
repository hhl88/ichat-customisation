package com.novomind.ecom.ichat.customisation.core.server.iagent;

import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent.IAgentServerUpdateDTO;

import java.io.Serializable;


public class IAgentServer implements Serializable {

    private String id;
    private String address;
    private String userAPI;

    private String password;
    private String clientId;
    private String secret;

    private IAgentServer() {

    }

    public IAgentServer(String id, String address, String userAPI, String password, String clientId, String secret) {
        this.id = id;
        this.address = address;
        this.userAPI = userAPI;
        this.password = password;
        this.clientId = clientId;
        this.secret = secret;
    }

    public static IAgentServer of(IAgentServerCreateDTO dto) {
        return new IAgentServer()
                .setAddress(dto.getAddress())
                .setUserAPI(dto.getUserAPI())
                .setPassword(dto.getPassword())
                .setClientId(dto.getClientId())
                .setSecret(dto.getSecret());
    }

    public static IAgentServer of(IAgentServerUpdateDTO dto) {
        return new IAgentServer()
                .setId(dto.getId())
                .setAddress(dto.getAddress())
                .setUserAPI(dto.getUserAPI())
                .setPassword(dto.getPassword())
                .setClientId(dto.getClientId())
                .setSecret(dto.getSecret());
    }

    public String getId() {
        return id;
    }

    public IAgentServer setId(String id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public IAgentServer setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getUserAPI() {
        return userAPI;
    }

    public IAgentServer setUserAPI(String userAPI) {
        this.userAPI = userAPI;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public IAgentServer setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public IAgentServer setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public IAgentServer setSecret(String secret) {
        this.secret = secret;
        return this;
    }
}

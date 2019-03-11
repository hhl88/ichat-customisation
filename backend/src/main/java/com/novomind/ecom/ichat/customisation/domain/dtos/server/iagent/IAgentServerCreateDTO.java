package com.novomind.ecom.ichat.customisation.domain.dtos.server.iagent;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class IAgentServerCreateDTO {

    @ApiModelProperty(required = true)
    @NotNull
    private String address;

    @ApiModelProperty
    private String userAPI;

    @ApiModelProperty
    private String password;

    @ApiModelProperty
    private String clientId;

    @ApiModelProperty
    private String secret;

    private IAgentServerCreateDTO() {
    }

    public IAgentServerCreateDTO(@NotNull String address, String userAPI, String password, String clientId, String secret) {
        this.address = address;
        this.userAPI = userAPI;
        this.password = password;
        this.clientId = clientId;
        this.secret = secret;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserAPI() {
        return userAPI;
    }

    public void setUserAPI(String userAPI) {
        this.userAPI = userAPI;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

}

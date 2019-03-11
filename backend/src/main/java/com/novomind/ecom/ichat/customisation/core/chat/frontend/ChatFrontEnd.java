package com.novomind.ecom.ichat.customisation.core.chat.frontend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend.FrontEndUpdateDTO;

import java.io.Serializable;


public class ChatFrontEnd implements Serializable {

    private String id;
    private String userId;
    private String name;

    private String iAgentServerId;

    private String cloudId;

    private String urlPath;

    private String demandInfoId;

    private int connectionType;

    public static ChatFrontEnd of(FrontEndCreateDTO dto) {
        return new ChatFrontEnd()
                .setName(dto.getName())
                .setConnectionType(dto.getConnectionType().ordinal())
                .setUrlPath(dto.getUrlPath());
    }

    public static ChatFrontEnd of(FrontEndUpdateDTO dto) {
        return new ChatFrontEnd()
                .setId(dto.getId())
                .setName(dto.getName())
                .setConnectionType(dto.getConnectionType().ordinal())
                .setUrlPath(dto.getUrlPath());
    }

    private ChatFrontEnd() {
    }

    public ChatFrontEnd(String id, String userId, String name, String iAgentServerId, String cloudId, String urlPath, String demandInfoId, int connectionType) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.iAgentServerId = iAgentServerId;
        this.cloudId = cloudId;
        this.urlPath = urlPath;
        this.demandInfoId = demandInfoId;
        this.connectionType = connectionType;
    }

    public String getId() {
        return id;
    }

    public ChatFrontEnd setId(String id) {
        this.id = id;
        return this;
    }

    @JsonIgnore
    public String getUserId() {
        return userId;
    }

    public ChatFrontEnd setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ChatFrontEnd setName(String name) {
        this.name = name;
        return this;
    }

    public String getIAgentServerId() {
        return iAgentServerId;
    }

    public ChatFrontEnd setIAgentServerId(String iAgentServerId) {
        this.iAgentServerId = iAgentServerId;
        return this;
    }

    public String getCloudId() {
        return cloudId;
    }

    public ChatFrontEnd setCloudId(String cloudId) {
        this.cloudId = cloudId;
        return this;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public ChatFrontEnd setUrlPath(String urlPath) {
        this.urlPath = urlPath;
        return this;
    }

    public String getDemandInfoId() {
        return demandInfoId;
    }

    public ChatFrontEnd setDemandInfoId(String demandInfoId) {
        this.demandInfoId = demandInfoId;
        return this;
    }

    public int getConnectionType() {
        return connectionType;
    }

    public ChatFrontEnd setConnectionType(int connectionType) {
        this.connectionType = connectionType;
        return this;
    }
}

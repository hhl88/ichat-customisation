package com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.server.iagent.IAgentServer;


public class FrontEndDTO {

    private String id;
    private String name;
    private IAgentServer iAgentServer;
    private String urlPath;
    private DemandInfo demandInfo;
    private int connectionType;

    private FrontEndDTO(){}

    public FrontEndDTO(String id, String name, IAgentServer iAgentServer, String urlPath, DemandInfo demandInfo, int connectionType) {
        this.id = id;
        this.name = name;
        this.iAgentServer = iAgentServer;
        this.urlPath = urlPath;
        this.demandInfo = demandInfo;
        this.connectionType = connectionType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonProperty(value="iAgentServer")
    public IAgentServer getiAgentServer() {
        return iAgentServer;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public DemandInfo getDemandInfo() {
        return demandInfo;
    }

    public int getConnectionType() {
        return connectionType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setiAgentServer(IAgentServer iAgentServer) {
        this.iAgentServer = iAgentServer;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public void setDemandInfo(DemandInfo demandInfo) {
        this.demandInfo = demandInfo;
    }

    public void setConnectionType(int connectionType) {
        this.connectionType = connectionType;
    }
}

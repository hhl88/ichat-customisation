package com.novomind.ecom.ichat.customisation.core.demandInfo;

import java.util.ArrayList;
import java.util.List;


public class DemandInfo {

  private String id;
  private List<DemandInfoItem> demandInfoList = new ArrayList<>();

    private DemandInfo() {

    }

    public DemandInfo(String id, List<DemandInfoItem> demandInfoList) {
        this.id = id;
        this.demandInfoList = demandInfoList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DemandInfoItem> getDemandInfoList() {
        return demandInfoList;
    }

    public void setDemandInfoList(List<DemandInfoItem> demandInfoList) {
        this.demandInfoList = demandInfoList;
    }
}

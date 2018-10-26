package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import java.util.List;

import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;

public interface DemandInfoDao {
  
  String insertDemandInfoList(List<DemandInfo> infoList);
  
  void updateInfoList(String id, List<DemandInfo> infoList);
  
  List<DemandInfo> findDemandInfoById(String id);

  String findIdByInfoList(List<DemandInfo> infoList);

}

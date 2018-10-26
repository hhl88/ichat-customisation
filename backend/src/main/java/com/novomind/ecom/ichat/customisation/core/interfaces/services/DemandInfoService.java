package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import java.util.List;

import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;

public interface DemandInfoService {
  
  String addDemandInFo(List<DemandInfo> demandInfoList);

  List<DemandInfo> findDemandInfoById(String id);
  
  String findDemandIdByInfo(List<DemandInfo> demandInfoList);

  void updateDemandInfo(String id, List<DemandInfo> demandInfoList);
  
}

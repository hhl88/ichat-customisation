package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfoItem;

import java.util.List;
import java.util.Optional;

public interface DemandInfoService {

  String addDemandInFo(List<DemandInfoItem> infoList);

  Optional<DemandInfo> findDemandInfoById(String id);

  void updateDemandInfo(DemandInfo demandInfo);
  
}

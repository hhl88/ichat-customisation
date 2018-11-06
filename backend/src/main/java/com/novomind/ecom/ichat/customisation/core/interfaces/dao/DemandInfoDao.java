package com.novomind.ecom.ichat.customisation.core.interfaces.dao;

import java.util.List;
import java.util.Optional;

import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfoItem;

public interface DemandInfoDao {

  String insertDemandInfoList(List<DemandInfoItem> infoList);
  
  void updateInfoList(DemandInfo demandInfo);

  Optional<DemandInfo> findDemandInfoById(String id);

}

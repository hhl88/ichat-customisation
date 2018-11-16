package com.novomind.ecom.ichat.customisation.core.components;

import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfoItem;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.DemandInfoDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.DemandInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandInfoServiceImpl implements DemandInfoService {
  @Autowired
  DemandInfoDao demandInfoDao;
  
  @Override
  public String addDemandInFo(List<DemandInfoItem> infoList) {
    return demandInfoDao.insertDemandInfoList(infoList);
  }
  
  @Override
  public Optional<DemandInfo> findDemandInfoById(String id) {
    return demandInfoDao.findDemandInfoById(id);
  }


  @Override
  public void updateDemandInfo(DemandInfo demandInfo) {
    demandInfoDao.updateInfoList(demandInfo);
  }
  
}

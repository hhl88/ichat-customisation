package com.novomind.ecom.ichat.customisation.core.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.DemandInfoDao;
import com.novomind.ecom.ichat.customisation.core.interfaces.services.DemandInfoService;

@Service
public class DemandInfoServiceImpl implements DemandInfoService {
  @Autowired
  DemandInfoDao demandInfoDao;
  
  @Override
  public String addDemandInFo(List<DemandInfo> demandInfoList) {
    String id = demandInfoDao.findIdByInfoList(demandInfoList);
    if(id == null)
      return demandInfoDao.insertDemandInfoList(demandInfoList);
    return id;
  }
  
  @Override
  public List<DemandInfo> findDemandInfoById(String id) {
    return demandInfoDao.findDemandInfoById(id);
  }

  @Override
  public String findDemandIdByInfo(List<DemandInfo> demandInfoList) {
    return demandInfoDao.findIdByInfoList(demandInfoList);
  }

  @Override
  public void updateDemandInfo(String id ,List<DemandInfo> demandInfoList) {
    demandInfoDao.updateInfoList(id, demandInfoList);
  }
  
}

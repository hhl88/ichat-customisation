package com.novomind.ecom.ichat.customisation.persistents;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.DemandInfoDao;
import com.novomind.ecom.ichat.customisation.persistents.mappers.DemandInfoRowMapper;

@Repository
public class DemandInfoDaoImpl extends BaseDao implements DemandInfoDao {

  @Override
  public String insertDemandInfoList(List<DemandInfo> info) {
    String query = "INSERT INTO demand_info(demand_info) VALUES(?)";
    return String.valueOf(getJdbcTemplate().update(query, new Gson().toJson(info)));
  }

  @Override
  public void updateInfoList(String id, List<DemandInfo> info) {
    String query = "UPDATE demand_info SET demand_info = ? WHERE id = ?";
    getJdbcTemplate().update(query, new Gson().toJson(info), id);
  }

  @Override
  public List<DemandInfo> findDemandInfoById(String id) {
    String query = "SELECT * FROM demand_info WHERE id = ?";
    return DataAccessUtils.singleResult(getJdbcTemplate().query(query, new DemandInfoRowMapper(), id));
  }

  @Override
  public String findIdByInfoList(List<DemandInfo> infoList) {
    String query = "SELECT id FROM demand_info WHERE demand_info = ?";
    return (String) getJdbcTemplate().queryForObject(query, new Object[] {new Gson().toJson(infoList)}, String.class);
  }

}

package com.novomind.ecom.ichat.customisation.persistents.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.google.gson.Gson;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;

public class DemandInfoRowMapper implements RowMapper<List<DemandInfo>>{
  
  @Nullable
  @Override
  public List<DemandInfo> mapRow(ResultSet rs, int rowNum) throws SQLException {
    return Arrays.asList(new Gson().fromJson(rs.getString("demand_info"), DemandInfo[].class));
  }

}

package com.novomind.ecom.ichat.customisation.persistents.mappers;

import com.google.gson.Gson;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfoItem;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class DemandInfoRowMapper implements RowMapper<DemandInfo> {

    @Nullable
    @Override
    public DemandInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return DemandInfo.builder()
                .id(rs.getString("id"))
                .demandInfoList(Arrays.asList(new Gson().fromJson(rs.getString("demand_info"), DemandInfoItem[].class)))
                .build();
    }

}

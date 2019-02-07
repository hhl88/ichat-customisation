package com.novomind.ecom.ichat.customisation.persistents.demandinfo;

import com.google.gson.Gson;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfo;
import com.novomind.ecom.ichat.customisation.core.demandInfo.DemandInfoItem;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.DemandInfoDao;
import com.novomind.ecom.ichat.customisation.persistents.base.BaseDao;
import com.novomind.ecom.ichat.customisation.persistents.mappers.DemandInfoRowMapper;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.novomind.ecom.ichat.customisation.constants.Constants.DEMAND_INFO_TABLE;

@Repository
public class DemandInfoDaoImpl extends BaseDao implements DemandInfoDao {

    @Override
    public String insertDemandInfoList(List<DemandInfoItem> info) {
        String generatedId = generateStringIdForTable(DEMAND_INFO_TABLE, 10);

        String query = "INSERT INTO " + DEMAND_INFO_TABLE + "(id, demand_info) VALUES(?, ?)";
        getJdbcTemplate().update(query, generatedId, new Gson().toJson(info));
        return generatedId;
    }

    @Override
    public void updateInfoList(DemandInfo demandInfo) {
        String query = "UPDATE " + DEMAND_INFO_TABLE + " SET demand_info = ? WHERE id = ?";
        getJdbcTemplate().update(query, new Gson().toJson(demandInfo.getDemandInfoList()), demandInfo.getId());
    }

    @Override
    public Optional<DemandInfo> findDemandInfoById(String id) {
        String query = "SELECT * FROM " + DEMAND_INFO_TABLE + " WHERE id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(getJdbcTemplate().query(query, new DemandInfoRowMapper(), id)));
    }

}

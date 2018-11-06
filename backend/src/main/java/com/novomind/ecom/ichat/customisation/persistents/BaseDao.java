package com.novomind.ecom.ichat.customisation.persistents;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.google.gson.Gson;

import com.novomind.ecom.ichat.customisation.core.common.StringIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class BaseDao extends JdbcDaoSupport {
    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @PostConstruct
    protected void initialize() {
        setDataSource(dataSource);
    }

    protected String generateStringIdForTable(String tableName, int idLength) {
        String idQuery = "SELECT COUNT(id) FROM " + tableName + " WHERE id LIKE ?";
        String generatedId;
        while (true) {
            generatedId = StringIDGenerator.generateStringId(idLength);
            int count = getJdbcTemplate().queryForObject(idQuery, new Object[]{generatedId}, Integer.class);
            if (count == 0) break;
        }
        return generatedId;
    }


}

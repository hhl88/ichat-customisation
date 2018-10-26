package com.novomind.ecom.ichat.customisation.persistents;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import com.google.gson.Gson;

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

  protected String toJson(Object obj) {
    Gson gson = new Gson();
    return gson.toJson(obj);
  }

}

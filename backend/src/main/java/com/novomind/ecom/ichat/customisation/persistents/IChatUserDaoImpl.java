package com.novomind.ecom.ichat.customisation.persistents;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import com.novomind.ecom.ichat.customisation.core.interfaces.dao.IChatUserDao;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.persistents.mappers.IChatUserRowMapper;

@Repository("userDao")
public class IChatUserDaoImpl extends BaseDao implements IChatUserDao {
  Logger logger = LoggerFactory.getLogger("IChatUserDao");

  @Override
  public String insertIChatUser(IChatUser user) {
    String query = "INSERT INTO ichat_user(email, password) VALUES(?, ?)";
    getJdbcTemplate().update(query, user.getEmail(), user.getPassword());
    return findIChatUserByEmail(user.getEmail()).get().getId();
  }

  @Override
  public void updatePassword(IChatUser user) {
    String query = "UPDATE ichat_user SET " + " password = ?" + " WHERE id = ?";
    getJdbcTemplate().update(query, user.getPassword(), user.getId());

  }

  @Override
  public Optional<IChatUser> findIChatUserById(String id) {
    logger.info("in USERDAO" );
    String query = "Select * FROM ichat_user WHERE id = ?";
    return Optional.ofNullable(DataAccessUtils.singleResult((getJdbcTemplate().query(query,  new IChatUserRowMapper(), id))));
  }

  @Override
  public Optional<IChatUser> findIChatUserByEmail(String email) {
    logger.info("in USERDAO findIChatUserByEmail" );

    String query = "Select * FROM ichat_user WHERE email LIKE ?";
    List<IChatUser> list= getJdbcTemplate().query(query,  new IChatUserRowMapper(), email);
    logger.info("in USERDAO findIChatUserByEmail after query" );

    IChatUser user = DataAccessUtils.singleResult(list);
    logger.info("in USERDAO findIChatUserByEmail single result" );

    return Optional.ofNullable(user);
  }

}

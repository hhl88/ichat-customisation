package com.novomind.ecom.ichat.customisation.persistents.user;

import com.novomind.ecom.ichat.customisation.core.interfaces.dao.IChatUserDao;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.persistents.base.BaseDao;
import com.novomind.ecom.ichat.customisation.persistents.mappers.IChatUserRowMapper;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.novomind.ecom.ichat.customisation.constants.Constants.ICHAT_USER_TABLE;

@Repository("userDao")
public class IChatUserDaoImpl extends BaseDao implements IChatUserDao {

    @Override
    public String insertIChatUser(IChatUser user) {
        String generatedId = generateStringIdForTable(ICHAT_USER_TABLE, 10);
        String query = "INSERT INTO " + ICHAT_USER_TABLE + "(id, email, password) VALUES(?, ?, ?)";
        getJdbcTemplate().update(query, generatedId, user.getEmail(), user.getPassword());
        return generatedId;
    }

    @Override
    public void updatePassword(IChatUser user) {
        String query = "UPDATE " + ICHAT_USER_TABLE + " SET " + " password = ?" + " WHERE id = ?";
        getJdbcTemplate().update(query, user.getPassword(), user.getId());

    }

    @Override
    public Optional<IChatUser> findIChatUserById(String id) {
        String query = "Select * FROM " + ICHAT_USER_TABLE + " WHERE id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult((getJdbcTemplate().query(query, new IChatUserRowMapper(), id))));
    }

    @Override
    public Optional<IChatUser> findIChatUserByEmail(String email) {
        System.out.println("email "+ email);
        String query = "Select * FROM " + ICHAT_USER_TABLE + " WHERE email LIKE ?";
        List<IChatUser> list = getJdbcTemplate().query(query, new IChatUserRowMapper(), email);
        IChatUser user = DataAccessUtils.singleResult(list);
        return Optional.ofNullable(user);
    }

}

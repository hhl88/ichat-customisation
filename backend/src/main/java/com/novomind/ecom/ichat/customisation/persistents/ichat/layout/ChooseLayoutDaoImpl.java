package com.novomind.ecom.ichat.customisation.persistents.ichat.layout;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChooseLayoutDao;
import com.novomind.ecom.ichat.customisation.persistents.base.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import static com.novomind.ecom.ichat.customisation.constants.Constants.CHOOSE_LAYOUT_TABLE;

@Repository
public class ChooseLayoutDaoImpl extends BaseDao implements ChooseLayoutDao {

    Logger log = LoggerFactory.getLogger(ChooseLayoutDao.class);

    @Override
    public void insert(ChatLayout layout) {
        String query = "INSERT INTO " + CHOOSE_LAYOUT_TABLE + "(user_id, chat_layout_id) "
                + " VALUES(?, ?)";
        try {
            getJdbcTemplate().update(query, layout.getUserId(), layout.getId());
        } catch (Exception e) {
            log.error("user cannot insert new chat layout");
        }

    }

    @Override
    public void changeToAnother(ChatLayout layout) {
        String query = "UPDATE " + CHOOSE_LAYOUT_TABLE
                + " SET chat_layout_id = ?"
                + " WHERE user_id = ?";
        try {
            getJdbcTemplate().update(query, layout.getId(), layout.getUserId());
        } catch (Exception e) {
            log.error("Cannot choose another layout");
        }

    }

    @Override
    public String findChatLayoutIdByUserId(String userId) {
        String query = "SELECT chat_layout_id FROM " + CHOOSE_LAYOUT_TABLE + " WHERE user_id = ?";
        try {
            return getJdbcTemplate().queryForObject(query, new Object[]{userId}, String.class);
        } catch (Exception e) {
            return null;
        }
    }

}

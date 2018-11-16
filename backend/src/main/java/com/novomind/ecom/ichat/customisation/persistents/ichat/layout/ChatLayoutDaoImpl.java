package com.novomind.ecom.ichat.customisation.persistents.ichat.layout;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.common.FontStyleConverter;
import com.novomind.ecom.ichat.customisation.core.interfaces.dao.ChatLayoutDao;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Font;
import com.novomind.ecom.ichat.customisation.persistents.base.BaseDao;
import com.novomind.ecom.ichat.customisation.persistents.mappers.ChatLayoutRowMapper;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.novomind.ecom.ichat.customisation.constants.Constants.CHAT_LAYOUT_TABLE;

@Repository
public class ChatLayoutDaoImpl extends BaseDao implements ChatLayoutDao {

    @Override
    public String insertChatLayout(ChatLayout chatLayout) {
        String generatedId = generateStringIdForTable(CHAT_LAYOUT_TABLE, 10);
        Font font = chatLayout.getFont();

        String query = "INSERT INTO " + CHAT_LAYOUT_TABLE + " ( "
                + " id, user_id, name, display_type, text_input_type, "
                + " button_type, logo, background_img, background_type, "
                + " font_family, font_size, font_styles) "
                + " VALUES ( "
                + " ?, ?, ?, ?, ?, "
                + " ?, ?, ?, ?, "
                + " ?, ?, ?)";
        getJdbcTemplate()
                .update(query,
                        generatedId, chatLayout.getUserId(), chatLayout.getName(), chatLayout.getDisplayType().ordinal(), chatLayout.getTextInputType().ordinal(),
                        chatLayout.getButtonType().ordinal(), chatLayout.getLogo(), chatLayout.getBackgroundImg(), chatLayout.getBackgroundType().ordinal(),
                        font.getFontFamily(), font.getFontSize(), Integer.valueOf(FontStyleConverter.fontStylesToBit(font.getFontStyles())));
        return generatedId;

    }

    @Override
    public void updateChatLayout(ChatLayout chatLayout) {
        String query = "UPDATE " + CHAT_LAYOUT_TABLE + " SET "
                + " name = ?,  display_type = ?, text_input_type = ?, button_type = ?, "
                + " logo = ?, background_img = ?, background_type = ?, "
                + " font_family = ?, font_size = ?, font_styles = ? "
                + " WHERE id = ?";
        Font font = chatLayout.getFont();
        logger.info("font " + font);
        logger.info("fontStyles " + FontStyleConverter.fontStylesToBit(font.getFontStyles()));

        getJdbcTemplate().update(query,
                chatLayout.getName(), chatLayout.getDisplayType().ordinal(), chatLayout.getTextInputType().ordinal(), chatLayout.getButtonType().ordinal(),
                chatLayout.getLogo(), chatLayout.getBackgroundImg(), chatLayout.getBackgroundType().ordinal(),
                font.getFontFamily(), font.getFontSize(), Integer.valueOf(FontStyleConverter.fontStylesToBit(font.getFontStyles())),
                chatLayout.getId());
    }

    @Override
    public Optional<ChatLayout> findChatLayoutById(String id) {
        String query = "Select * FROM " + CHAT_LAYOUT_TABLE + " WHERE id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(getJdbcTemplate().query(query, new ChatLayoutRowMapper(), id)));
    }

    @Override
    public List<ChatLayout> findChatLayoutByUserId(String userId) {
        String query = "Select * FROM " + CHAT_LAYOUT_TABLE + " WHERE user_id = ?";
        return getJdbcTemplate().query(query, new ChatLayoutRowMapper(), userId);
    }


}

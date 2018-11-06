package com.novomind.ecom.ichat.customisation.persistents.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.novomind.ecom.ichat.customisation.domain.datatypes.ButtonDisplay;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Font;
import com.novomind.ecom.ichat.customisation.domain.datatypes.LayoutDisplay;
import com.novomind.ecom.ichat.customisation.domain.datatypes.TextAreaDisplay;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.common.FontStyleConverter;

public class ChatLayoutRowMapper implements RowMapper<ChatLayout> {

    @Nullable
    @Override
    public ChatLayout mapRow(ResultSet rs, int rowNum) throws SQLException {
        String fontStyles = String.valueOf(rs.getInt("font_styles"));

        return ChatLayout.builder()
                .id(rs.getString("id"))
                .name(rs.getString("name"))
                .displayType(LayoutDisplay.valueOf(rs.getString("display_type")))
                .textInputType(TextAreaDisplay.valueOf(rs.getString("text_input_type")))
                .buttonType(ButtonDisplay.valueOf(rs.getString("button_type")))
                .logo(rs.getBlob("logo").toString())
                .backgroundImg(rs.getBlob("background_img").toString())
                .font(new Font(rs.getString("font_family"), rs.getInt("font_size"), FontStyleConverter.bitToFontStyles(fontStyles)))
                .build();

    }

}

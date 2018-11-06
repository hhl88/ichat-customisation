package com.novomind.ecom.ichat.customisation.persistents.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.novomind.ecom.ichat.customisation.core.common.TypeConverter;
import com.novomind.ecom.ichat.customisation.domain.datatypes.*;
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
                .displayType(LayoutDisplay.values()[TypeConverter.bitToStyleIndex(rs.getInt("display_type"))])
                .textInputType(TextAreaDisplay.values()[TypeConverter.bitToStyleIndex(rs.getInt("text_input_type"))])
                .buttonType(ButtonDisplay.values()[TypeConverter.bitToStyleIndex(rs.getInt("button_type"))])
                .logo(rs.getBlob("logo").toString())
                .backgroundImg(rs.getBlob("background_img").toString())
                .backgroundType(BackgroundDisplay.values()[TypeConverter.bitToStyleIndex(rs.getInt("background_type"))])
                .font(new Font(rs.getString("font_family"), rs.getInt("font_size"), FontStyleConverter.bitToFontStyles(fontStyles)))
                .build();

    }

}

package com.novomind.ecom.ichat.customisation.persistents.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.common.FontStyleConverter;

public class ChatLayoutRowMapper implements RowMapper<ChatLayout> {

  @Nullable
  @Override
  public ChatLayout mapRow(ResultSet rs, int rowNum) throws SQLException {
    ChatLayout chatLayout = new ChatLayout();
    chatLayout.setId(String.valueOf(rs.getLong("id")));
    chatLayout.setName(rs.getString("name"));

    chatLayout.setDisplayType(rs.getString("display_type"));
    chatLayout.setTextInputType(rs.getString("text_input_type"));
    chatLayout.setButtonType(rs.getString("button_type"));
    chatLayout.setLogo(rs.getString("logo"));
    
    chatLayout.setBackgroundImg(rs.getString("background_img"));
    chatLayout.setBackgroundType(rs.getString("background_type"));
    
    chatLayout.setFontFamily(rs.getString("font_family"));
    chatLayout.setFontSize(rs.getInt("font_size"));
    
    String fontStyles = String.valueOf(rs.getInt("font_styles"));
    chatLayout.setFontStyles(FontStyleConverter.bitToFontStyles(fontStyles));

    return chatLayout;
  }

}

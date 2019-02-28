package com.novomind.ecom.ichat.customisation.persistents.mappers;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.novomind.ecom.ichat.customisation.core.chat.layout.ChatLayout;
import com.novomind.ecom.ichat.customisation.core.common.FontStyleConverter;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Bubble;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ChatLayoutRowMapper implements RowMapper<ChatLayout> {
    Logger log = LoggerFactory.getLogger(getClass());
    @Nullable
    @Override
    public ChatLayout mapRow(ResultSet rs, int rowNum) throws SQLException {
        Byte b = rs.getByte("font_styles");
        String fontStyles = Integer.toBinaryString(b);
/*        Type type = new TypeToken<Map<String, Bubble>>(){}.getType();
        RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(Map.class, "@class");
//
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();
        Map<String, Bubble> decoded = gson.fromJson(json, new TypeToken<Map<String, PersonData>>(){}.getType());*/

        return ChatLayout.builder()
                .id(rs.getString("id"))
                .userId(rs.getString("user_id"))
                .name(rs.getString("name"))
                .displayType(rs.getInt("display_type"))
                .textInputType(rs.getInt("text_input_type"))
                .buttonType(rs.getInt("button_type"))
                .logo(convertBlobToString(rs.getBlob("logo")))
                .backgroundImg(convertBlobToString(rs.getBlob("background_img")))
                .backgroundType(rs.getInt("background_type"))
                .font(new Font(rs.getString("font_family"), rs.getInt("font_size"), FontStyleConverter.bitToFontStyles(fontStyles)))
                .bubbleStyle(new Gson().fromJson(rs.getString("bubble_style"), new TypeToken<HashMap<String, Bubble>>() {
                }.getType()))
                .build();

    }

    private String convertBlobToString(Blob blob) throws SQLException {
        return blob != null ? new String(blob.getBytes(1l, (int) blob.length())) : null;
    }

}

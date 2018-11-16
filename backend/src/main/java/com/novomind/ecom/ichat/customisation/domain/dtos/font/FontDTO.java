package com.novomind.ecom.ichat.customisation.domain.dtos.font;

import com.novomind.ecom.ichat.customisation.domain.datatypes.Font;
import com.novomind.ecom.ichat.customisation.domain.datatypes.FontStyle;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class FontDTO {

    private String fontFamily;
    private int fontSize;
    private List<String> fontStyles;

    private FontDTO() {}

    public FontDTO(String fontFamily, int fontSize, List<String> fontStyles) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.fontStyles = fontStyles;
    }

    public static FontDTO of(Font font) {
        List<String> fontStyles = font.getFontStyles()
                .stream()
                .map(style -> String.valueOf(style.ordinal()))
                .collect(Collectors.toList());
        return new FontDTO(font.getFontFamily(), font.getFontSize(), fontStyles);
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public List<String> getFontStyles() {
        return fontStyles;
    }
}

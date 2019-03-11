package com.novomind.ecom.ichat.customisation.domain.dtos.font;

import com.novomind.ecom.ichat.customisation.core.common.FontStyleConverter;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Font;
import com.novomind.ecom.ichat.customisation.domain.datatypes.FontStyle;

import java.util.Set;

public class FontDTO {

    private String fontFamily;
    private String fontSize;
    private Set<FontStyle> fontStyles;

    private FontDTO() {
    }

    public FontDTO(String fontFamily, String fontSize, Set<FontStyle> fontStyles) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.fontStyles = fontStyles;
    }

    public static FontDTO of(Font font) {
        return new FontDTO(font.getFontFamily(), font.getFontSize(), FontStyleConverter.intToFontStyles(font.getFontStyles()));
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public String getFontSize() {
        return fontSize;
    }

    public Set<FontStyle> getFontStyles() {
        return fontStyles;
    }
}

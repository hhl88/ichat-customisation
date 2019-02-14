package com.novomind.ecom.ichat.customisation.domain.dtos.font;

import com.novomind.ecom.ichat.customisation.core.common.FontStyleConverter;
import com.novomind.ecom.ichat.customisation.domain.datatypes.Font;
import com.novomind.ecom.ichat.customisation.domain.datatypes.FontStyle;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FontDTO {

    private String fontFamily;
    private int fontSize;
    private Set<FontStyle> fontStyles;

    private FontDTO() {}

    public FontDTO(String fontFamily, int fontSize, Set<FontStyle> fontStyles) {
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

    public int getFontSize() {
        return fontSize;
    }

    public Set<FontStyle> getFontStyles() {
        return fontStyles;
    }
}

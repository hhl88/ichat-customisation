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
    private List<FontStyle> fontStyles;

    private FontDTO() {}

    public FontDTO(String fontFamily, int fontSize, List<FontStyle> fontStyles) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.fontStyles = fontStyles;
    }

    public static FontDTO of(Font font) {
        return new FontDTO(font.getFontFamily(), font.getFontSize(), font.getFontStyles());
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public List<FontStyle> getFontStyles() {
        return fontStyles;
    }
}

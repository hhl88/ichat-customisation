package com.novomind.ecom.ichat.customisation.domain.datatypes;

import com.novomind.ecom.ichat.customisation.core.common.FontStyleConverter;

import java.util.Set;

public class Font {

    private String fontFamily;
    private String fontSize;
    private Set<Integer> fontStyles;

    public Font(String fontFamily, String fontSize, Set<Integer> fontStyles) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.fontStyles = fontStyles;
    }

    public static Font of(String fontFamily, String fontSize, Set<FontStyle> fontStyles) {
        return new Font(fontFamily, fontSize, FontStyleConverter.frontStylesToInt(fontStyles));
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public Set<Integer> getFontStyles() {
        return fontStyles;
    }

    public void setFontStyles(Set<Integer> fontStyles) {
        this.fontStyles = fontStyles;
    }
}

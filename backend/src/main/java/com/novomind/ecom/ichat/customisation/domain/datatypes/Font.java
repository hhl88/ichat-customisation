package com.novomind.ecom.ichat.customisation.domain.datatypes;

import com.novomind.ecom.ichat.customisation.core.common.FontStyleConverter;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString
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

}

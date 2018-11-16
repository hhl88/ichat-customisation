package com.novomind.ecom.ichat.customisation.domain.datatypes;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Font {

    private String fontFamily;
    private int fontSize;
    private List<FontStyle> fontStyles;

    public Font(String fontFamily, int fontSize, List<FontStyle> fontStyles) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.fontStyles = fontStyles;
    }

}

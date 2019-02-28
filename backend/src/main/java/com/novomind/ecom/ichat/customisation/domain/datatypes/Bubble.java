package com.novomind.ecom.ichat.customisation.domain.datatypes;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Bubble {

    private String backgroundColor;
    private String borderColor;
    private String borderRadius;
    private String fontColor;

    public Bubble(String backgroundColor, String borderColor, String borderRadius, String fontColor) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.borderRadius = borderRadius;
        this.fontColor = fontColor;
    }
}

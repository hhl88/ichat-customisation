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
    private String color;

    public Bubble(String backgroundColor, String borderColor, String borderRadius, String color) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.borderRadius = borderRadius;
        this.color = color;
    }
}

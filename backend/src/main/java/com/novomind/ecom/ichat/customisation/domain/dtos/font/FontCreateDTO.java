package com.novomind.ecom.ichat.customisation.domain.dtos.font;

import com.novomind.ecom.ichat.customisation.domain.datatypes.FontStyle;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;


public class FontCreateDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private String fontFamily;
  
  @ApiModelProperty(required = true)
  @NotNull
  private int fontSize;
  
  private List<FontStyle> fontStyles;

    private FontCreateDTO() {
    }

    public FontCreateDTO(@NotNull String fontFamily, @NotNull int fontSize, List<FontStyle> fontStyles) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.fontStyles = fontStyles;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public List<FontStyle> getFontStyles() {
        return fontStyles;
    }

    public void setFontStyles(List<FontStyle> fontStyles) {
        this.fontStyles = fontStyles;
    }

}

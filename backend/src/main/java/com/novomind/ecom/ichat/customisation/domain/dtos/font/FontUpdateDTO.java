package com.novomind.ecom.ichat.customisation.domain.dtos.font;

import com.novomind.ecom.ichat.customisation.domain.datatypes.FontStyle;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;


public class FontUpdateDTO {
  
  @ApiModelProperty(required = true)
  @NotNull
  private String fontFamily;
  
  @ApiModelProperty(required = true)
  @NotNull
  private String fontSize;
  
  private List<FontStyle> fontStyles;

  private FontUpdateDTO() {
  }

  public FontUpdateDTO(@NotNull String fontFamily, @NotNull String fontSize, List<FontStyle> fontStyles) {
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

  public String getFontSize() {
    return fontSize;
  }

  public void setFontSize(String fontSize) {
    this.fontSize = fontSize;
  }

  public List<FontStyle> getFontStyles() {
    return fontStyles;
  }

  public void setFontStyles(List<FontStyle> fontStyles) {
    this.fontStyles = fontStyles;
  }
}

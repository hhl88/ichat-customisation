package com.novomind.ecom.ichat.customisation.core.common;

import java.util.ArrayList;
import java.util.List;

import com.novomind.ecom.ichat.customisation.domain.datatypes.FontStyle;

public final class FontStyleConverter {
   
  private FontStyleConverter() {}
  
  private static int fontStyleToBit(FontStyle fontStyle) {
    return 1 << fontStyle.ordinal();
  }
    
  public static String fontStylesToBit(List<FontStyle> fontStyles) {
    int result = fontStyles.stream().mapToInt(fontStyle -> fontStyleToBit(fontStyle)).sum();
    return Integer.toBinaryString(result);
  }
  
  public static  List<FontStyle> bitToFontStyles(String bit) {
    int index = bit.indexOf("1");
    List<FontStyle> fontStyles = new ArrayList<>();
    while (index >= 0) {
          fontStyles.add(FontStyle.values()[index]);
        index = bit.indexOf("1", index + 1);
    }
    return fontStyles;
  }
  
}

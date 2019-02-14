package com.novomind.ecom.ichat.customisation.core.common;

import com.novomind.ecom.ichat.customisation.domain.datatypes.FontStyle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class FontStyleConverter {
   
  private FontStyleConverter() {}
  
  private static int fontStyleToBit(FontStyle fontStyle) {
    return 1 << fontStyle.ordinal();
  }
    
//  public static int fontStylesToBit(List<FontStyle> fontStyles) {
//    return fontStyles.stream().mapToInt(fontStyle -> fontStyleToBit(fontStyle)).sum();
////    return Integer.toBinaryString(result);
//  }

  public static int fontStylesToBit(Set<Integer> fontStyles) {
    return fontStyles.stream().mapToInt(i -> 1 << i).sum();
//    return Integer.toBinaryString(result);
  }
  
//  public static  List<FontStyle> bitToFontStyles(String bit) {
//    int index = bit.indexOf("1");
//    List<FontStyle> fontStyles = new ArrayList<>();
//    while (index >= 0) {
//          fontStyles.add(FontStyle.values()[index ]);
//        index = bit.indexOf("1", index + 1);
//    }
//    return fontStyles;
//  }

  public static Set<Integer> bitToFontStyles(String bit) {
    int index = bit.indexOf("1");
    Set<Integer> fontStyles = new HashSet<>();
    while (index >= 0) {
      fontStyles.add(FontStyle.values()[index ].ordinal());
      index = bit.indexOf("1", index + 1);
    }
    return fontStyles;
  }


  public static Set<Integer> frontStylesToInt(Set<FontStyle> fontStyles) {
    return fontStyles.stream().map(style -> FontStyle.valueOf(style.name()).ordinal()).collect(Collectors.toSet());
  }

  public static Set<FontStyle> intToFontStyles(Set<Integer> fontStyles) {
    return fontStyles.stream().map(i -> FontStyle.values()[i]).collect(Collectors.toSet());
  }


}

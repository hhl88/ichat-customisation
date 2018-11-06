package com.novomind.ecom.ichat.customisation.core.common;

import com.novomind.ecom.ichat.customisation.domain.datatypes.FontStyle;

public final class TypeConverter {

    private TypeConverter() {}

    public static String styleToBit(int style) {
        return Integer.toBinaryString(style);
    }

    public static int bitToStyleIndex(int bit) {
        return Integer.parseInt(String.valueOf(bit), 2);
    }
}

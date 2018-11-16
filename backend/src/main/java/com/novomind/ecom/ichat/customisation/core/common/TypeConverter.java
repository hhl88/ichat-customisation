package com.novomind.ecom.ichat.customisation.core.common;

public final class TypeConverter {

    private TypeConverter() {}

    public static String styleToBit(int style) {
        return Integer.toBinaryString(style);
    }

    public static int bitToStyleIndex(int bit) {
        return Integer.parseInt(String.valueOf(bit), 2);
    }
}

package com.novomind.ecom.ichat.customisation.core.common;

public final class StringIDGenerator {

    private static final String BASE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateStringId(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(BASE.charAt((int) (Math.floor(Math.random() * BASE.length()))));
        }
        return builder.toString();
    }

}

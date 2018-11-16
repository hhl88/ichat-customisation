package com.novomind.ecom.ichat.customisation.core.common;

import com.novomind.ecom.ichat.customisation.constants.Constants;

import java.util.Random;

public final class StringGenerator {

  private final static String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

  private StringGenerator() {
  }

  public static String generateRandomString() {
    StringBuilder sb = new StringBuilder();
    Random rnd = new Random();
    for (int i = 0; i < Constants.RANDOM_PASSWORD_LENGTH; i++) {
      sb.append(CHAR_LIST.charAt(rnd.nextInt(CHAR_LIST.length())));
    }
    return sb.toString();
  }
}

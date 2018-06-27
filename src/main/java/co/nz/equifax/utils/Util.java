package co.nz.equifax.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Util {

  public static String getRandomKey(){
    return RandomStringUtils.randomAlphanumeric(10);
  }
}

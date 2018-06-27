package co.nz.equifax.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Util {

  public static String getRandomKeyAlphanumeric(int size){
    return RandomStringUtils.randomAlphanumeric(size);
  }
  
  public static String getRandomKeyNumereic(int size){
	    return RandomStringUtils.randomNumeric(size);
	  }
}

package com.nexiilabs.stp.util;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.log4j.Logger;

public class PasswordGenerator {
	 private static final Random RANDOM = new SecureRandom();
	 private static final Logger log = Logger.getLogger(PasswordGenerator.class);
	  
	  public static final int PASSWORD_LENGTH = 8;
	 
	  public static String generateRandomPassword()
	  {
	      String letters = "abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNOPQRSTUVWXYZ0123456789@$";

	      String pw = "";
	      for (int i=0; i<PASSWORD_LENGTH; i++)
	      {
	          int index = (int)(RANDOM.nextDouble()*letters.length());
	          pw += letters.substring(index, index+1);
	      }
	       log.info("PasswordGenerator::generateRandomPassword()::Executed");
	      return pw;
	  }
}

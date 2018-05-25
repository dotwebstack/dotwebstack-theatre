package org.dotwebstack.theatre.pwencrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encrypt {

  private final static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
  
   public static void main(String[] args) {
		
    if (args.length != 1) {
      System.out.println("Usage: pwencrypt.jar <plain-password>");
    }
    else {
      System.out.println(String.format("Plain password   : %s", args[0]));
      System.out.println(String.format("Encoded password : %s", encoder.encode(args[0])));
    }
    
   }
}
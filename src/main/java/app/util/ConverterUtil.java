package app.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConverterUtil {

	
    public static String senhaForte(String senha) {
   	 boolean achouNumero = false;
        boolean achouMaiuscula = false;
        boolean achouMinuscula = false;
        boolean achouSimbolo = false;
   	
     if (senha.length() <= 6) {
           return "10";
     }else {
         for (char c : senha.toCharArray()) {
            if (c >= '0' && c <= '9') {
                achouNumero = true;
            } else if (c >= 'A' && c <= 'Z') {
                achouMaiuscula = true;
            } else if (c >= 'a' && c <= 'z') {
                achouMinuscula = true;
            } else {
                achouSimbolo = true;
            }
         }
          if(achouNumero && achouMaiuscula && achouMinuscula && achouSimbolo) {
        	    return "100"; 
          }else if(achouSimbolo && achouMinuscula && achouMaiuscula){
 	            return "85";   
          }else if(achouNumero && achouMinuscula && achouMaiuscula){
      	        return "85";
          }else if(achouNumero && achouMinuscula && achouSimbolo && !achouMaiuscula) {      
        	    return "70";
          } else if(achouNumero && !achouMinuscula && achouSimbolo && achouMaiuscula) {      
            	return "70";
          }else if(achouNumero && achouMinuscula  && !achouSimbolo && !achouMaiuscula){
        	    return "50";
          }else if(achouNumero && !achouMinuscula  && !achouSimbolo && achouMaiuscula){
        	    return "50";
          }else if(achouNumero && !achouMinuscula  && achouSimbolo && !achouMaiuscula){
        	    return "50";
          }else if(!achouNumero && achouMinuscula && achouMaiuscula && !achouSimbolo){
	            return "40";   
          }else {
        	    return "25";
         }
      }
   }
    
    public static String  criptografaSenha(String senha) {
		   MessageDigest algorithm;
		try {
			   algorithm = MessageDigest.getInstance("SHA-256");

			   byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

		       StringBuilder hexString = new StringBuilder();
		       for (byte b : messageDigest) {
		         hexString.append(String.format("%02X", 0xFF & b));
		       }
		       String senhahex = hexString.toString();

		       System.out.println(senhahex);
		       
		       return senhahex;
		       
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return senha;
	      
	   
    }
}

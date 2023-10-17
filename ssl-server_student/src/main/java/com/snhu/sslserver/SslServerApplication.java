//Project 2 Tiffany McDonnell
package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;


@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}
}

@RestController
class ServerController{ 

    //Used NoSuchAlgorithmExeption to run as Java application
    public static String hashFunc(String newData) throws NoSuchAlgorithmException{
    	
    //created hash
   	 MessageDigest md = MessageDigest.getInstance("SHA-256");
   	 byte[] biteDigest = md.digest(newData.getBytes(StandardCharsets.UTF_8));
   	 BigInteger stringNum = new BigInteger(1, biteDigest);
   	 StringBuilder stringAsNum = new StringBuilder(stringNum.toString(16));
   	 
   	 while(stringAsNum.length() < 32) {
   		 stringAsNum.insert(0,'0');
   	 }

        return stringAsNum.toString();
        
   }
    
    @RequestMapping("/hash")
    public String myHash() throws NoSuchAlgorithmException{
    	String data = "Hello Wolrd Check Sum!";
        String newCheckSum = hashFunc(data);
        
        return "<p>data:"+ data + " : SHA-256 " + newCheckSum;
    }
}

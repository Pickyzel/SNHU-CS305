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
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}

@RestController
class ServerController{
//FIXME:  Add hash function to return the checksum value for the data string that should contain your name.    

    //Used NoSuchAlgorithmExeption to run as Java application
    public static String hashFunc(String data) throws NoSuchAlgorithmException{
    	
   	 MessageDigest md = MessageDigest.getInstance("SHA-256");
   	 byte[] biteDigest = md.digest(data.getBytes(StandardCharsets.UTF_8));
   	 BigInteger num = new BigInteger(1, biteDigest);
   	 StringBuilder stringAsNum = new StringBuilder(num.toString(16));
   	 
   	 while(stringAsNum.length() < 32) {
   		 stringAsNum.insert(0,'0');
   	 }

        return stringAsNum.toString();
        
   }
    
    @RequestMapping("/hash")
    public String myHash() throws Exception{
    	//String data = "Hello Joe Smith!";
    	String data = "Hello Tiffany McDonnell!";
        String checkSum = hashFunc(data);
        
        return "<p>data:"+ data + " : SHA-256 " + checkSum;
    }
}


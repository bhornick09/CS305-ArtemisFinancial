package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.security.MessageDigest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class CheckSumController {
	@RequestMapping("/api")
	public String getCheckSum() {
		String data = "Brandon Hornick";
   	 	String checksum = calculateChecksum(data);
        return "<p>data: " + data + "</p><p>Name of Algorithm Used: CheckSum </p><p>Checksum Value: " + checksum + "</p>";
	}
	private String calculateChecksum(String data) {
		//try catch
		try {
			// Create a SHA-256 digest
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// Convert checksum to a byte array
			byte[] hashBytes = digest.digest(data.getBytes());
            // Convert the byte array to hex string
            return bytesToHex(hashBytes);
   	 } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	private String bytesToHex(byte[] bytes){
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
	}
}
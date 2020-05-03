package db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public interface Security {

    public static void main(String[] args) throws Exception{

        String paswoord = "Hello";
        byte[] salt = createSalt();
        System.out.println("Hash: " + generateHash(paswoord, salt));
        System.out.println("Paswoord: " + paswoord);
        System.out.println("Salt: " + salt);
    }

    public static String generateHash(String paswoord, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // Message digests are secure one-way hash functions that take arbitrary-sized data and output a fixed-length hash value.
        // Ondersteunt volgende algoritmen: MD5, SHA-1, SHA-256
        digest.reset();
        digest.update(salt);
        // eerst salt toevoegen, dan pas hash toepassen
        byte[] hash = digest.digest(paswoord.getBytes());
        // digest.digest() levert een array van bytes op -> omzetten naar hexadecimale notatie
        return bytesToStringHex(hash);
    }

    private static String bytesToStringHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        // A string buffer is like a String, but its length and content can be modified through certain method calls.
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            // 0xff is the hexadecimal number FF which has a integer value of 255.
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static byte[] createSalt() {
        byte[] bytes = new byte[20];
        // Je kan de grootte van de array zelf kiezen.
        SecureRandom random = new SecureRandom();
        // SecureRandom is veiliger dan Random voor het genereren van willekeurige getallen.
        // Bij Random is de gegenereerde waarde gemakkelijker te voorspellen.
        random.nextBytes(bytes);
        // The nextBytes() function takes a user-supplied byte array and fills it with random bytes.
        return bytes;
    }
}

/*
Validate Password
While Storing the password
- Generate a long random salt using SecureRandom.
- Use the Hash function such as SHA256 to hash both Salt and Password together.
- Save both the Salt and the Hash separately in the database.
While Validating the password
- Retrieve the Salt and Hash from the database.
- Use the same Hash function (SHA256) which is used while generating the hash.
- Generate a new Hash with the new password provided and the Salt retrieved from the database.
- Now compare the new hash with the hash from the database. If they match, then the password provided is correct. Otherwise, the password is incorrect.
 */

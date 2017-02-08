package dungeons_and_dragons.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sean Peel
 */
public class Password {

    protected String password = "";

    public Password(String password) {
        this.password = password;
    }

    public String hash() {
        byte[] hashPass;
        StringBuilder hashHexPass = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            //Obtain SHA-256 version of password
            md.update(password.getBytes("UTF-8"));
            hashPass = md.digest();

            //StringBuilder to convert 32 bit SHA-256 output to 64 bit hex string
            //in order to adhere to MYSQL SHA standards
            hashHexPass = new StringBuilder();

            //Conversion
            for (byte b : hashPass) {
                hashHexPass.append(String.format("%02x", b));
            }

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            //TODO change
            Logger.getLogger(UserAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hashHexPass.toString();
    }

}

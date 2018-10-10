/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author axel.medina
 * @author edson.aju
 * @since descripción Clase utilizada para manejar la encriptacion de las contraseñas
 */
public class getMD5 implements Serializable{

    private static final long serialVersionUID = 2023713583332762025L;

    /* <p> Metodo que recibe la contraseña obtiene la instancia MD5 y devuelve la secuencia encriptada <p>*/
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

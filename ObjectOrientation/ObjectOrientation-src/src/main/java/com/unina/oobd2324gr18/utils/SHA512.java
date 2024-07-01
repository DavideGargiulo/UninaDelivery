package com.unina.oobd2324gr18.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Fornisce metodi per generare password sicure utilizzando l'algoritmo di hashing SHA-512.
 * Include funzionalità per aggiungere un sale alle password prima dell'hashing per aumentare la sicurezza.
 *
 * @author DavideGargiulo
 */
public class SHA512 {
  /**
   * Genera una password sicura applicando l'hash SHA-512 alla password fornita, con l'aggiunta di un sale.
   *
   * @param password La password in chiaro da hashare.
   * @param salt Il sale da utilizzare nell'hashing della password.
   * @return La password hashata in formato esadecimale.
   */
  public static String getSecurePassword(final String password, byte[] salt) {
    String generatedPassword = null;
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
      messageDigest.update(salt);
      byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
      StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      generatedPassword = stringBuilder.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generatedPassword;
  }

  /**
   * Genera un sale casuale da utilizzare nell'hashing delle password.
   *
   * @return Un array di byte contenente il sale.
   */
  public static byte[] getSalt() {
    SecureRandom secureRandom = new SecureRandom();
    byte[] salt = new byte[16];
    secureRandom.nextBytes(salt);
    return salt;
  }
}
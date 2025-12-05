package com.sena.sitea.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.ejb.Stateless;

/**
 * Servicio para hashing de contraseñas y generación de credenciales temporales.
 * Utiliza SHA-256 con salt aleatorio para mayor seguridad.
 */
@Stateless
public class PasswordHashService {

    /**
     * Genera una contraseña temporal aleatoria de 12 caracteres.
     */
    public String generateTemporaryPassword() {
        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < 12; i++) {
            password.append(charset.charAt(random.nextInt(charset.length())));
        }
        
        return password.toString();
    }

    /**
     * Genera un hash seguro de la contraseña usando SHA-256.
     * Retorna: salt_base64 + ":" + hash_base64
     */
    public String hashPassword(String password) {
        try {
            // Generar salt aleatorio
            byte[] salt = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);
            
            // Hash: SHA-256(salt + password)
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            
            // Retornar: salt + ":" + hash en Base64
            String saltBase64 = Base64.getEncoder().encodeToString(salt);
            String hashBase64 = Base64.getEncoder().encodeToString(hash);
            
            return saltBase64 + ":" + hashBase64;
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Valida una contraseña contra un hash previamente guardado.
     */
    public boolean verifyPassword(String password, String hashedPassword) {
        try {
            if (!hashedPassword.contains(":")) {
                return false;
            }
            
            String[] parts = hashedPassword.split(":");
            if (parts.length != 2) {
                return false;
            }
            
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] storedHash = Base64.getDecoder().decode(parts[1]);
            
            // Recompute hash
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] computedHash = md.digest(password.getBytes("UTF-8"));
            
            // Compare
            return MessageDigest.isEqual(computedHash, storedHash);
        } catch (Exception e) {
            return false;
        }
    }
}

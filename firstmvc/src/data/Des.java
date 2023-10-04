package data;
import java.security.Key;  
import java.security.SecureRandom;  
  
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;  
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;  
public class Des extends Coder {  
    
    public static final String ALGORITHM = "DESede";  
  
    /** 
     * ת����Կ<br> 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    private static Key toKey(byte[] key) throws Exception {  
    	SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
//        DESKeySpec dks = new DESKeySpec(key);  
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);  
//        SecretKey secretKey = keyFactory.generateSecret(dks);  
        
        
        return secretKey;  
    }  
  
    /** 
     * ���� 
     *  
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decrypt(byte[] data, String key) throws Exception {  
        Key k = toKey(decryptBASE64(key));  
  
        Cipher cipher = Cipher.getInstance(ALGORITHM);  
        cipher.init(Cipher.DECRYPT_MODE, k);  
  
        return cipher.doFinal(data);  
    }  

    public static byte[] encrypt(byte[] data, String key) throws Exception {  
        Key k = toKey(decryptBASE64(key));  
        Cipher cipher = Cipher.getInstance(ALGORITHM);  
        cipher.init(Cipher.ENCRYPT_MODE, k);  
  
        return cipher.doFinal(data);  
    }  
  
    /** 
     * ������Կ 
     *  
     * @return 
     * @throws Exception 
     */  
    public static String initKey() throws Exception {  
        return initKey(null);  
    }  
  
    /** 
     * ������Կ 
     *  
     * @param seed 
     * @return 
     * @throws Exception 
     */  
    public static String initKey(String seed) throws Exception {  
        SecureRandom secureRandom = null;   
        if (seed != null) {  
            secureRandom = new SecureRandom(decryptBASE64(seed));  
        } else {  
            secureRandom = new SecureRandom();  
        }  
  
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);  
        kg.init(secureRandom);  
  
        SecretKey secretKey = kg.generateKey();  
  
        return encryptBASE64(secretKey.getEncoded());  
    }  
}  

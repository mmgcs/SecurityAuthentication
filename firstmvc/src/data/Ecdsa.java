package data;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.EncryptedPrivateKeyInfo;



//import org.apache.commons.codec.binary.Hex;

public class Ecdsa extends Coder{
	
	
	private static String pubKey;
	private static String priKey;
	private static String signText;
	
	public static String getPubkey(){
		return pubKey;
	}
	public static String getPrikey(){
		return priKey;
	}
	public static String Sign(String src,String priKey){
		String sign = "";
		try {
			//ECPrivateKey ecPrivateKey = decryptBASE64(priKey);
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decryptBASE64(priKey));
			KeyFactory keyFactory = KeyFactory.getInstance("EC");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance("SHA1withECDSA");
			signature.initSign(privateKey);
			signature.update(src.getBytes());
			byte[] result = signature.sign();
			sign =  encryptBASE64(result);
		} catch (Exception e) {
			// TODO: handle exception
			sign = "error";
		}
		return sign;
	}
	public static boolean SignCheck(String src,String sign,String pubKey){
		boolean bool = true;
		try {
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decryptBASE64(pubKey));
			KeyFactory keyFactory = KeyFactory.getInstance("EC");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			Signature signature = Signature.getInstance("SHA1withECDSA");
			signature.initVerify(publicKey);
			signature.update(src.getBytes());
			bool = signature.verify(decryptBASE64(sign));
			
		} catch (Exception e) {
			// TODO: handle exception
			 bool = false;
		}
		return bool;
		
		
	}
	public static void jdkECDSA(){
		try {
			//1.��ʼ����Կ
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
			keyPairGenerator.initialize(256);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			ECPublicKey ecPublicKey = (ECPublicKey)keyPair.getPublic();
			pubKey = encryptBASE64(ecPublicKey.getEncoded());//��Կ��64λ����
			ECPrivateKey ecPrivateKey = (ECPrivateKey)keyPair.getPrivate();
			priKey = encryptBASE64(ecPrivateKey.getEncoded());//˽Կ��64λ����
			
			
			//2.ִ��ǩ��
//			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
//			KeyFactory keyFactory = KeyFactory.getInstance("EC");
//			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
//			Signature signature = Signature.getInstance("SHA1withECDSA");
//			signature.initSign(privateKey);
//			signature.update(src.getBytes());
//			byte[] result = signature.sign();
//			Log.d("sign", encryptBASE64(result));
			
			
			//3.��֤ǩ��
//			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
//			keyFactory = KeyFactory.getInstance("EC");
//			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
//			signature = Signature.getInstance("SHA1withECDSA");
//			signature.initVerify(publicKey);
//			signature.update(src.getBytes());
//			boolean bool = signature.verify(result);
//			if (bool) {
//				Log.d("result","1");
//			}else {
//				Log.d("result","0");
//			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

}

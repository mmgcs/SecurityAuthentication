package data;

public class DesTest {
	public static void main(String[] args) throws Exception {
		
		String key =Des.initKey();
		System.out.println(key);
		String encry = Des.encryptBASE64(Des.encrypt("1ds".getBytes(), key));
		System.out.println(encry);
		String decry = new String(Des.decrypt(Des.decryptBASE64(encry), key));
		
		System.out.println(decry);
		
		
	}

}

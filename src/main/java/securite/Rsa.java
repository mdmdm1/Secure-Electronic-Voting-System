package securite;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class Rsa {
	private PrivateKey privatekey;
	private PublicKey publickey;
	
	public void init() {
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			KeyPair pair = generator.generateKeyPair();
			privatekey = pair.getPrivate();
			publickey = pair.getPublic();
		}catch(Exception ignored){}
	}
	public PrivateKey getPrivatekey() {
		return privatekey;
	}
	public void setPrivatekey(String privatekey) {
		initfromString(privatekey,"");
	}
	public PublicKey getPublickey() {
		return publickey;
	}
	public void setPublickey(String publickey) {
		initfromString("",publickey);
	}
	public String ENPrivatekey() {
		return encode(privatekey.getEncoded());
	}

	public String ENPublickey() {
		return encode(publickey.getEncoded());
	}
	
	public String encode(byte []data) {
		return Base64.getEncoder().encodeToString(data);
	}
	
	public static byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}
	
	public String encrypt(String message)throws Exception{
		byte []messageToBytes = message.getBytes();
		Cipher cipher =Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE,publickey);
		byte[] encryptedByte = cipher.doFinal(messageToBytes);
		return encode(encryptedByte);
	}
	public String decrypt(String encryptedMessage)throws Exception{
		byte[] encryptedBytes = decode(encryptedMessage);
		Cipher cipher =Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE,privatekey);
		byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
		return new String(decryptedMessage,"UTF8");
		
		
	}
	public void initfromString(String pr, String pu) {
		try {
			X509EncodedKeySpec KeySpecPublic = new X509EncodedKeySpec(decode(pu));
			PKCS8EncodedKeySpec keySpecPrivate=new PKCS8EncodedKeySpec(decode(pr));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			privatekey = keyFactory.generatePrivate(keySpecPrivate);
			publickey = keyFactory.generatePublic(KeySpecPublic);
		}catch(Exception ignored) {}
	}
	/*public static void main(String[]args) {
		Rsa A1= new Rsa();
		//Rsa A2= new Rsa();
		//A1.initfromString("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIseRFlw0AmUmot9zYZ2SDq87YGjMm6BTjiMZRGMfx5TRDucNmxy28dIz8/8u50RESCngP26B5qYr6SlVgsXzgvt2FByvZXuc1wgCel7O86Zc3/waYzuwFi0F68l31DVBnQG2qxacpLqG2n4sPbwzaz/l1phGI6rpA0Yu3SSpQqdAgMBAAECgYBM4AnW/t8HHEYWZQtIjV42jyYTqBH7PJrz5qkG/INWR58B7b2pSgMm6ZZkkX0/OyoiyE0gaEdBZ7RPoEme0aW64z6gRZCND1HSg8gsudfWDGKtrxPR8Q4gkgOHwe/i0pFWpQ+b7WOR0TTHG5xwN1PTvMxYb+q1N3GNuJx0dg1ihQJBAL15H8jjIO0L/+NskwKLds3KUuMeH8TLK/TkbpmNtCp+5L0UZU+kr9oWeMlzLoHJ65RwnjRA1G2h3fdZXgN7obcCQQC79vhAmxgZlIgdvzckWIxNPh/JDEekoX0iIr91sZv4XS4hZvtZN0ovaHZ+etL1/OJVf6rKeI7b/izEqpJx8KZLAkAcD5Y/gDPeJ4rMOcZD+DeuHqrL7Cg0Uwq9iB9BKpVmPcjOn/ipJPDOUcmtvBtFKYx0PYGQp5FOc0yWV2vvBB5dAkAnKpcgHzEuY1zZlfaPhEP39HH7GqvzChNhAYyToaa2YfQy0ZqeRH8Y0dOfF0jOKRugZTHZbrWV8aiycjiBtXtxAkEAnKtMbaZouN01q67o86UOchvQ+guNQH3EMPHEgIjdPT1re0g8NyPO3dx/Ih6pve4TZfFXxsJpXisxRZpx6QC62A==","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLHkRZcNAJlJqLfc2Gdkg6vO2BozJugU44jGURjH8eU0Q7nDZsctvHSM/P/LudEREgp4D9ugeamK+kpVYLF84L7dhQcr2V7nNcIAnpezvOmXN/8GmM7sBYtBevJd9Q1QZ0BtqsWnKS6htp+LD28M2s/5daYRiOq6QNGLt0kqUKnQIDAQAB");
		A1.initfromString("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIseRFlw0AmUmot9zYZ2SDq87YGjMm6BTjiMZRGMfx5TRDucNmxy28dIz8/8u50RESCngP26B5qYr6SlVgsXzgvt2FByvZXuc1wgCel7O86Zc3/waYzuwFi0F68l31DVBnQG2qxacpLqG2n4sPbwzaz/l1phGI6rpA0Yu3SSpQqdAgMBAAECgYBM4AnW/t8HHEYWZQtIjV42jyYTqBH7PJrz5qkG/INWR58B7b2pSgMm6ZZkkX0/OyoiyE0gaEdBZ7RPoEme0aW64z6gRZCND1HSg8gsudfWDGKtrxPR8Q4gkgOHwe/i0pFWpQ+b7WOR0TTHG5xwN1PTvMxYb+q1N3GNuJx0dg1ihQJBAL15H8jjIO0L/+NskwKLds3KUuMeH8TLK/TkbpmNtCp+5L0UZU+kr9oWeMlzLoHJ65RwnjRA1G2h3fdZXgN7obcCQQC79vhAmxgZlIgdvzckWIxNPh/JDEekoX0iIr91sZv4XS4hZvtZN0ovaHZ+etL1/OJVf6rKeI7b/izEqpJx8KZLAkAcD5Y/gDPeJ4rMOcZD+DeuHqrL7Cg0Uwq9iB9BKpVmPcjOn/ipJPDOUcmtvBtFKYx0PYGQp5FOc0yWV2vvBB5dAkAnKpcgHzEuY1zZlfaPhEP39HH7GqvzChNhAYyToaa2YfQy0ZqeRH8Y0dOfF0jOKRugZTHZbrWV8aiycjiBtXtxAkEAnKtMbaZouN01q67o86UOchvQ+guNQH3EMPHEgIjdPT1re0g8NyPO3dx/Ih6pve4TZfFXxsJpXisxRZpx6QC62A==","");
		A1.initfromString("","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLHkRZcNAJlJqLfc2Gdkg6vO2BozJugU44jGURjH8eU0Q7nDZsctvHSM/P/LudEREgp4D9ugeamK+kpVYLF84L7dhQcr2V7nNcIAnpezvOmXN/8GmM7sBYtBevJd9Q1QZ0BtqsWnKS6htp+LD28M2s/5daYRiOq6QNGLt0kqUKnQIDAQAB");
		//A2.init();
		/*System.err.println("Public = "+A1.ENPublickey());
		System.err.println("Private = "+A1.ENPrivatekey()+"\n");
		System.err.println("Public = "+A2.ENPublickey());
		System.err.println("Private = "+A2.ENPrivatekey()+"\n");
		try {
			System.out.println(A1.encrypt("Bonjour"));
			System.out.println(A1.decrypt("N9gvdjCoZhaFAe0aseyU+RfQMw7H+9RT1IjibFHzzlJJlm9uGMp+rQC9W31rqac8fMbYLC2kXNkq+0cWGiMkPA0jeCctyWNtTezgqu8SsNtyUToYO348THQf/Ejp86WIBPkGWHx+08nd2kCjFJGDH0iHNn1C+znLDysR2h/pC08="));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
}

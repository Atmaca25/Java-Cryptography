package javacryptology;
import java.security.*;
import javax.crypto.*;
import java.util.Scanner;
public class JavaCryptology 
{
    public static void main(String[] args) throws Exception
    {
        Scanner scn = new Scanner(System.in);
        
        System.out.print("Input   :");
        String plaintext = scn.nextLine();

        String algoritma= "RSA";

        

        KeyPairGenerator generate = KeyPairGenerator.getInstance("RSA");

        generate.initialize(512);

        // Üreticisi RSA için standart olarak 1024 bitlik anahtar

        // üretmektedir. Farklı büyüklükte anahtar üretilmesi için initialize()

        // komutuna ihtiyaç duyulur.

        // NOT: RSA açık anahtar kripto sisteminde kullanılan anahtarlar en az

        // 512 bit uzunluğunda olmalıdır.

        // KeyPair anahtar = generate.genKeyPair();

        
        KeyPair anahtar = KeyPairGenerator.getInstance(algoritma).generateKeyPair();

        PrivateKey hususiAnahtar = anahtar.getPrivate();

        PublicKey umumiAnahtar = anahtar.getPublic();

        System.out.println("Anahtar Bilgileri: " + hususiAnahtar);

        System.out.println("Orjinal metin : " + plaintext);
        
        System.out.println("Orjinal metin Byte : " + ByteToString(plaintext.getBytes()));

        Cipher cipher = Cipher.getInstance(algoritma);

        cipher.init(Cipher.ENCRYPT_MODE, hususiAnahtar);

        byte[] sifreliMetin = cipher.doFinal(plaintext.getBytes());

        System.out.println("Sifrelenmis metin : " + ByteToString(sifreliMetin));

        cipher.init(Cipher.DECRYPT_MODE, umumiAnahtar);

        byte[] desifreliMetin = cipher.doFinal(sifreliMetin);

       // System.out.println("Desifrelenmis metin : " + ByteToString(desifreliMetin));
        System.out.println("Desifrelenmis metin : " + desifreliMetin);
    }
    

    public static String ByteToString(byte[] buffer)
    {
        StringBuilder s = new StringBuilder();

        for (byte b: buffer)

        {

            String temp = Integer.toHexString(0x00FF & b);

            if( temp.length() == 1)
            {
                 s.append("0"+temp);
            }
            else
            {
                 s.append(temp);
            }

        }
           return s.toString();
    }
}
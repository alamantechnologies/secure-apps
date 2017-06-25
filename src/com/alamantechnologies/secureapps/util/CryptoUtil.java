/* 
 * AL-AMAN TECHNOLOGIES CONFIDENTIAL
 * AUTHOR : MUHAMMAD SWALAH A A.
 * Unpublished Copyright (c) 2016-2020 AL-AMAN TECHNOLOGIES, All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of AL-AMAN TECHNOLOGIES. The
 * intellectual and technical concepts contained herein are proprietary to AL-AMAN TECHNOLOGIES and 
 * may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret
 * or copyright law. Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from AL-AMAN TECHNOLOGIES.  Access to the
 * source code contained herein is hereby forbidden to anyone except current AL-AMAN TECHNOLOGIES 
 * employees, managers or contractors who have executed Confidentiality and Non-disclosure agreements
 * explicitly covering such access.
 *
 * The copyright notice above does not evidence any actual or intended publication or disclosure of
 * this source code, which includes information that is confidential and/or proprietary, and is a 
 * trade secret, of AL-AMAN TECHNOLOGIES. ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, 
 * PUBLIC PERFORMANCE, OR PUBLIC DISPLAY OF OR THROUGH USE OF THIS SOURCE CODE WITHOUT THE EXPRESS
 * WRITTEN CONSENT OF COMPANY IS STRICTLY PROHIBITED, AND IN VIOLATION OF APPLICABLE LAWS AND 
 * INTERNATIONAL TREATIES.  THE RECEIPT OR POSSESSION OF THIS SOURCE CODE AND/OR RELATED INFORMATION
 * DOES NOT CONVEY OR IMPLY ANY RIGHTS TO REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO 
 * MANUFACTURE, USE, OR SELL ANYTHING THAT IT MAY DESCRIBE, IN WHOLE OR IN PART.
 */
package com.alamantechnologies.secureapps.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
/** 
 * Encryption and Decryption of String data; PBE(Password Based Encryption and Decryption)
 * @author Vikram
 */
public class CryptoUtil
{

    Cipher ecipher;
    Cipher dcipher;
    // 8-byte Salt
    byte[] salt = {
        (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
        (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
    };
    // Iteration count
    int iterationCount = 19;
    public CryptoUtil() { 

    }

    /**
     * 
     * @param secretKey Key used to encrypt data
     * @param plainText Text input to be encrypted
     * @return Returns encrypted text
     * 
     */
    public String encrypt(String secretKey, String plainText) 
            throws NoSuchAlgorithmException, 
            InvalidKeySpecException, 
            NoSuchPaddingException, 
            InvalidKeyException,
            InvalidAlgorithmParameterException, 
            UnsupportedEncodingException, 
            IllegalBlockSizeException, 
            BadPaddingException{
        //Key generation for enc and desc
        KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
         // Prepare the parameter to the ciphers
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

        //Enc process
        ecipher = Cipher.getInstance(key.getAlgorithm());
        ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);      
        String charSet="UTF-8";       
        byte[] in = plainText.getBytes(charSet);
        byte[] out = ecipher.doFinal(in);
        String encStr=new sun.misc.BASE64Encoder().encode(out);
        return encStr;
    }
     /**     
     * @param secretKey Key used to decrypt data
     * @param encryptedText encrypted text input to decrypt
     * @return Returns plain text after decryption
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidKeyException
     */
    public String decrypt(String secretKey, String encryptedText)
     throws NoSuchAlgorithmException, 
            InvalidKeySpecException, 
            NoSuchPaddingException, 
            InvalidKeyException,
            InvalidAlgorithmParameterException, 
            UnsupportedEncodingException, 
            IllegalBlockSizeException, 
            BadPaddingException, 
            IOException{
         //Key generation for enc and desc
        KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
         // Prepare the parameter to the ciphers
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
        //Decryption process; same key will be used for decr
        dcipher=Cipher.getInstance(key.getAlgorithm());
        dcipher.init(Cipher.DECRYPT_MODE, key,paramSpec);
        byte[] enc = new sun.misc.BASE64Decoder().decodeBuffer(encryptedText);
        byte[] utf8 = dcipher.doFinal(enc);
        String charSet="UTF-8";     
        String plainStr = new String(utf8, charSet);
        return plainStr;
    }    
    public static void main(String[] args) throws Exception {
        CryptoUtil cryptoUtil=new CryptoUtil();
        String key3   = "6Iv0p2=3tI9SXqRDyL!Z0oS[F2x=/h6VXF|r4cb5H3k1VXU81r3J6O1xQox40b0";
        String decKey = "6Iv0p2=3tI9SXqRDyL!Z0oS[F2x=/h6VXF|r4cb5H3k1VXU81r3J6O1xQox40b0";
        String key4 = "S&XE6&Ek)8yr]Jb4[3AZ4X4K>y%8/u;ss}54.Ip09=nvF4:cB2H3v4GZgE]e}7P";
        String plain="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\n" +
"gK/Zdw+y";
        final String _k="P1vcg0jznOQK2XTtW+98h39uQUPu|2V:8J9qt\"110<144Cj.dq*Cd|m9N`5G,Bf";
        //String enc=cryptoUtil.encrypt(key3, plain);
        System.out.println("Original text: "+plain);
        //System.out.println("Encrypted text: "+enc);
        String plainAfter=cryptoUtil.decrypt(_k, plain);
        System.out.println("Original text after decryption: "+plainAfter);
    }
}
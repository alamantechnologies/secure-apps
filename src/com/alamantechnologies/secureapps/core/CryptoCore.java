/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alamantechnologies.secureapps.core;

/**
 *
 * @author MUHAMMAD SWALAH A A
 * AL-AMAN TECHNOLOGIES
 */
//import alaman.fileencdec.GUI.FileEncDecGUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class CryptoCore {

    public static Cipher createCipher(char[] password,
            int nMode,
            byte[] baSalt,
            int nInterations) throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            InvalidKeyException,
            InvalidAlgorithmParameterException,
            NoSuchPaddingException {
        Cipher cipher = null;

        PBEParameterSpec pbeParamSpec = new PBEParameterSpec(baSalt, nInterations);

        PBEKeySpec pbeKeySpec = new PBEKeySpec(password);
        SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithSHA1AndDESede");
        SecretKey key = keyFac.generateSecret(pbeKeySpec);

        cipher = Cipher.getInstance("PBEWithSHA1AndDESede");
        cipher.init(nMode, key, pbeParamSpec);

        return cipher;
    }
    static BigDecimal bt=new BigDecimal("0");

    public static void doCrypto(InputStream in, OutputStream out, Cipher cipher) throws IOException 
    {
        String completedVal;
//        FileEncDecGUI a=new FileEncDecGUI();
        CipherOutputStream cos = new CipherOutputStream(out, cipher);
        byte[] baChunck = new byte[128];

        int nRead = 0;

        for (nRead = 0; nRead >= 0; nRead = in.read(baChunck)) {
            cos.write(baChunck, 0, nRead);
            completedVal = calc(nRead);
        }
        in.close();
        cos.close();
        bt = new BigDecimal("0");
    }
    public static int parse(String src)
    {
        if(src.contains("."))
        {
            src=src.substring(0,src.indexOf('.'));
        }
        int start = 0; // where to start iterating
        boolean negative = false; // negative or not
        switch (src.charAt(0))
        {
            case '-':
                negative = true;
                start = 1;
                break;
            case '+':
                start = 1;
                break;
            // default: do nothing
        }
        int number = 0;
        for (int i = start; i < src.length(); i++)
        {
            number = number * 10 + Character.digit(src.charAt(i), 10);
        }
        if (negative)
        {
            number = -number;
        }
        return number;
    }
    /*Methode to get percentage*/
    static int getPer(String size,BigDecimal value)
    {
        int per;
        float siz;
        float val;
        if(parse(size)>=1)
        {
            siz = Float.parseFloat(size);
            val = Float.parseFloat(value.toString());
            per = (int)((val/siz)*100);
        }
        else
        {
            per=100;
        }
        return per;
    }
    /*Methode to display status of file processed in progress bar*/
    static String calc(int byt) 
    {
        BigDecimal mb;
        bt = bt.add(new BigDecimal(byt));
        mb = bt.divide(new BigDecimal("1048576"));
        mb=mb.setScale(2,RoundingMode.HALF_UP);
        return mb.toString();
    }

    public static void doFileOperation(File fileIn,
            File fileOut,
            int nMode,
            char[] password,
            byte[] baSalt,
            int nIterations)
    {
        try
        {
            Cipher cipher = createCipher(password, nMode, baSalt, nIterations);
            doCrypto(new FileInputStream(fileIn),
                    new FileOutputStream(fileOut),
                    cipher);
        } 
        catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException | IOException ex) 
        {
            LogManager.logErr(ex);
            Logger.getLogger(CryptoCore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void encrypt(String pass,String fileIn,String fileOut)
    {
        char[] password = pass.toCharArray();
        byte[] baSalt = "'~7&amp;03~/.".getBytes();
        int nIterations = 1;
        File fileToEncrypt = new File(fileIn);
        File fileEncrypted = new File(fileOut);
        doFileOperation( fileToEncrypt,
                fileEncrypted,
                Cipher.ENCRYPT_MODE,
                password,
                baSalt,
                nIterations);
    }
    public static void decrypt(String pass,String fileIn,String fileOut)
    {
        char[] password = pass.toCharArray();
        byte[] baSalt = "'~7&amp;03~/.".getBytes();
        int nIterations = 1;
        File fileEncrypted = new File(fileIn);
        File fileDecrypted = new File(fileOut);
        doFileOperation(fileEncrypted,
                fileDecrypted,
                Cipher.DECRYPT_MODE,
                password,
                baSalt,
                nIterations);
    }
    public static String generateFileName(String filePath,String name)
    {
        int index;
        index = filePath.lastIndexOf(".");
        filePath = new StringBuilder(filePath).replace(index, index+1,name+".").toString();
        return filePath;
    }
}


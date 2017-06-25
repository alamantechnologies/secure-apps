/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alamantechnologies.secureapps.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

/**
 *
 * @author MUHAMMAD SWALAH A A
 */
public class LogManager 
{
    private static final String _k="P1vcg0jznOQK2XTtW+98h39uQUPu|2V:8J9qt\"110<144Cj.dq*Cd|m9N`5G,Bf";
    private static void createLogDirIfNotExist()
    {
        File theDir = new File("LOG");
        // if the directory does not exist, create it
        if (!theDir.exists()) 
        {
            try
            {
                theDir.mkdir();
            } 
            catch(SecurityException se)
            {
                JOptionPane.showMessageDialog(null, "err:#"+se.hashCode(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void logErr(Exception ex)
    {
        createLogDirIfNotExist();
        Writer writer = new StringWriter();
        PrintWriter printWriter1 = new PrintWriter(writer);
        ex.printStackTrace(printWriter1);
        String err = writer.toString();
        
        FileWriter fileWriter = null;
        PrintWriter printWriter;
        File file;
        String date;
        
        DateFormat dateFormat = new SimpleDateFormat("[yyyy-MMM-dd HH:mm:ss]");
        Date dat = new Date();
        CryptoUtil enc = new CryptoUtil();
        date = String.valueOf(dateFormat.format(dat));
        try
        {
            file = new File("LOG/ErrorLog.log");
            fileWriter = new FileWriter(file,true);
            printWriter = new PrintWriter(fileWriter);
            /*printWriter.println(date);*/
            /*printWriter.println(enc.encrypt(_k, date));*/
            /*printWriter.println(err);*/
            printWriter.println(enc.encrypt(_k, date+err));
            printWriter.println("---------------------");
               
        }
        catch(IOException e)
        {
        } 
        catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex1) {
            Logger.getLogger(LogManager.class.getName()).log(Level.SEVERE, null, ex1);
        }
        finally
        {   
            try
            {
                fileWriter.flush();
                fileWriter.close(); 
            } catch (IOException ex1) {
                Logger.getLogger(LogManager.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
        
    }
}

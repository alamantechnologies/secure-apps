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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
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
            //printWriter.println(date+err);
            printWriter.println("---------------------");
               
        }
        /*
        catch(Exception e)
        {
            
        }*/
        catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex1) {
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

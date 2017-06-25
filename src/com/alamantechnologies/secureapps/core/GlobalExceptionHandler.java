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
package com.alamantechnologies.secureapps.core;


import com.alamantechnologies.secureapps.util.LogManager;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author AL-AMAN TECHNOLOGIES
 */
public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler{
    

    @Override
    public void uncaughtException(Thread t, Throwable e) 
    {
        //To change body of generated methods, choose Tools | Templates.
        handle(e);
    }

    public void handle(Throwable ex) 
    {
        try 
        {
            // insert your e-mail code here
            LogManager.logErr((Exception) ex);
        } 
        catch (Throwable t) 
        {
            // don't let the exception get thrown out, will cause infinite looping!
        }
    }


    public static void registerExceptionHandler() 
    {
        Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());
        System.setProperty("sun.awt.exception.handler", GlobalExceptionHandler.class.getName());
        System.out.println("<<<<Global Exceptin Handler registered>>>>>");
    }
}

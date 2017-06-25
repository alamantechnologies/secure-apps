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

//import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author AL-AMAN TECHNOLOGIES
 */
public class Gen_Util 
{
    static Window w = new Window(null);
    public static void showErrorDialog(String errMsg)
    {
        w.getToolkit().beep();
        JOptionPane.showMessageDialog(null, errMsg, "Error",JOptionPane.ERROR_MESSAGE);
    }
    public static void showSuccessDialog(String sucMsg)
    {
        JOptionPane.showMessageDialog(null, sucMsg, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void showInformationDialog(String information)
    {
        JOptionPane.showMessageDialog(null, information, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    public static int showConfirmDialog(String conMsg)
    {
        return JOptionPane.showConfirmDialog(null, conMsg, "Confirm",JOptionPane.YES_NO_OPTION);
    }
    public static void showErrorDialog(Component component,String errMsg)
    {
        w.getToolkit().beep();
        JOptionPane.showMessageDialog(component, errMsg, "Error",JOptionPane.ERROR_MESSAGE);
    }
    public static void showSuccessDialog(Component component,String sucMsg)
    {
        JOptionPane.showMessageDialog(component, sucMsg, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void showInformationDialog(Component component,String information)
    {
        JOptionPane.showMessageDialog(component, information, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    public static int showConfirmDialog(Component component,String conMsg)
    {
        return JOptionPane.showConfirmDialog(component, conMsg, "Confirm",JOptionPane.YES_NO_OPTION);
    }
    /**
     * setting the date to current date.
     * @param dateChooser
     */
    /*public static void setToCurrentDate(JDateChooser dateChooser) {
        dateChooser.setDate(new Date());
    }
    
    public static String getDate(JDateChooser dateChooser)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        return sdf.format(dateChooser.getDate());
    }*/
}

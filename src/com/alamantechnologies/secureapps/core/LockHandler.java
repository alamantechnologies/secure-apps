/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * File temp = new File(fileLocation);
        String location = temp.getParent()+"\\";
        String actualfileName = temp.getName();
        String backFileName = temp.getName()+".bak";
        //String encLoc = 
        
        
        //CryptoCore.encrypt(encKey, fileLocation, fileLocation+".out");
        /*
        File temp = new File(fileLocation);
        System.out.println(temp.getName());
        //df.txt
        System.out.println(temp.getAbsoluteFile());
        //C:\Users\swala\Desktop\df.txt
        System.out.println(temp.getAbsolutePath());
        //C:\Users\swala\Desktop\df.txt
        System.out.println(temp.getParentFile());
        //C:\Users\swala\Desktop
        */
 /**/
 
package com.alamantechnologies.secureapps.core;

import com.alamantechnologies.secureapps.db.DB;
import com.alamantechnologies.secureapps.util.Gen_Util;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author swala
 */
public class LockHandler 
{
    private static DB db = new DB();
    private static String fileName;
    private static String backFileName;
    private static String fileLocation;
    private static String filePath;
    private static String encFilepath;
    private static String decFilePath;
    private static File appExe;
    private static File backFile;
    
    public static boolean lockApp(String appLocation)
    {
        boolean isValid = false;
        boolean status = false;
        appExe = new File(appLocation);
        
        filePath = appExe.getAbsolutePath();
        fileLocation = appExe.getParent()+"\\";
        fileName = appExe.getName();
        backFileName = fileName+".bak";
        encFilepath = fileLocation+fileName+".enc";
        
        if(db.checkRecord("LOCKED_APPS_TBL", "APP_NAME='"+fileName+"'")==false )
        {
            isValid = true;
        }
        else if(db.getValue("LOCK_STATUS", "LOCKED_APPS_TBL", "APP_NAME='"+fileName+"'").equalsIgnoreCase("FALSE"))
        {
            isValid = true;
        }
        else
        {
            Gen_Util.showInformationDialog("Already Locked!");
        }
        if(isValid)
        {
            
            CryptoCore.encrypt(UserCredentials.getEncKey(), filePath, encFilepath);

            //
            backFile = new File(fileLocation+backFileName);
            if(backFile.exists())
            {
                backFile.delete();
            }
            //actual exe to bak
            status = RenameCore.renameFile(filePath, fileLocation+backFileName);
            //enc file to actual file name
            if(status)
            {
                status = RenameCore.renameFile(encFilepath, filePath);
                if(status)
                {
                    if(db.checkRecord("LOCKED_APPS_TBL", "APP_NAME='"+fileName+"'"))
                    {
                        status = db.updateTable("LOCKED_APPS_TBL", "LOCK_STATUS=TRUE", "APP_NAME='"+fileName+"'");
                    }
                    else
                    {
                        status = db.insertTable("LOCKED_APPS_TBL", "APP_NAME,APP_LOCATION,LOCK_STATUS", "'"+fileName+"','"+filePath+"',TRUE");
                    }
                }

            }
        }
        
        
        
        return status;
    }
    public static boolean unLockApp(String appLocation)
    {
        boolean status = false;
        appExe = new File(appLocation);
        
        filePath = appExe.getAbsolutePath();
        fileLocation = appExe.getParent()+"\\";
        fileName = appExe.getName();
        backFileName = fileName+".bak";
        encFilepath = fileLocation+fileName+".enc";
        decFilePath = fileLocation+fileName+".dec";
        
        if(db.checkRecord("LOCKED_APPS_TBL", "APP_NAME='"+fileName+"'") && db.getValue("LOCK_STATUS", "LOCKED_APPS_TBL", "APP_NAME='"+fileName+"'").equalsIgnoreCase("TRUE"))
        {
            CryptoCore.decrypt(UserCredentials.getEncKey(), filePath, decFilePath);

    /*       //enc exe to encbak
           status = RenameCore.renameFile(filePath, fileLocation+backFileName);*/
            //delete enc file (with actual app name)
            status = appExe.delete();
            //enc file to actual file name
            if(status)
            {
                status = RenameCore.renameFile(decFilePath, filePath);
                if(status)
                {
                    status = db.updateTable("LOCKED_APPS_TBL", "LOCK_STATUS=FALSE", "APP_NAME='"+fileName+"'");
                    
                }
            }
        }
        else
        {
            Gen_Util.showInformationDialog("Already UnLocked!");
        }
        
        
        
        return status;
    }
    public static void getLockedAppData(JTable appLockedAppList_JTable)
    {
        String slNo;
        String appName;
        String appLocation;
        String lockStatus;
        try {
            Connection connection;
            Statement statement;
            connection = db.getConnection();
            statement = connection.createStatement();
            ResultSet resultset;
            resultset = statement.executeQuery("SELECT * FROM LOCKED_APPS_TBL ORDER BY APP_ID");
            DefaultTableModel tModel = (DefaultTableModel) appLockedAppList_JTable.getModel();
            tModel.setRowCount(0);
            while (resultset.next()) {

                slNo = resultset.getString("APP_ID");
                appName = resultset.getString("APP_NAME");
                appLocation = resultset.getString("APP_LOCATION");
                lockStatus = resultset.getString("LOCK_STATUS");
                if(lockStatus.equalsIgnoreCase("TRUE"))
                {
                    lockStatus = "LOCKED";
                }
                else
                {
                    lockStatus = "UNLOCKED";
                }
                Object row[] = {slNo, appName, appLocation, lockStatus};
                tModel.addRow(row);
            }

            statement.close();
            resultset.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
    }
    public static void main(String[] args) {
//        String loc = "C:\\Users\\swala\\Desktop\\df.txt";
//        File temp = new File(loc);
//        String newFile = temp.getParent()+"\\"+"newTextFile.txt";
//        System.out.println(RenameCore.renameFile(loc, newFile));

        File appExe = new File("C:\\Users\\swala\\Desktop\\newTextFile.txt");
        
        filePath = appExe.getAbsolutePath();
        fileLocation = appExe.getParent()+"\\";
        fileName = appExe.getName();
        backFileName = fileName+".bak";
        encFilepath = fileLocation+fileName+".enc";
        decFilePath = fileLocation+fileName+".dec";
        //File  backFile = new File(fileLocation+backFile);
        System.out.println();
    }
}

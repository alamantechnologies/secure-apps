/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alamantechnologies.secureapps.core;

import java.io.File;
import java.util.Arrays;

/**
 *
 * @author Muhammad Swalah
 */
public class RenameCore 
{
    public static File[] getFiles(String path)
    {
        File folder = new File(path);
        
        File[] files= folder.listFiles();
        
        Arrays.sort(files);
        return files;
    }
    public static boolean renameFile(String source,String newName)
    {
        boolean status;
        File newFile = new File(newName);
        File oldFile = new File(source);
        status=oldFile.renameTo(newFile);
        return status;
    }
    public static String getNewName(String oldName,String extension)
    {
        return oldName+"."+extension;
    }
}

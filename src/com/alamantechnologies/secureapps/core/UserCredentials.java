/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alamantechnologies.secureapps.core;

/**
 *
 * @author swala
 */
public class UserCredentials {
    private static final String adminUserName = "app_admin";
    private static final String adminPassword = "p@ssw0r6";
    private static final String encKey = "B57A9B54CCA25A4CA41EC6A83BFAE";
    
    public static String getEncKey() {
        return encKey;
    }

    public static String getAdminUserName() {
        return adminUserName;
    }

    public static String getAdminPassword() {
        return adminPassword;
    }
    
    
    
}

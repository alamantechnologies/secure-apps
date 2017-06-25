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
package com.alamantechnologies.secureapps.db;

import com.alamantechnologies.secureapps.util.LogManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author Muhammad Swalah
 */
public class DatabaseConnection_DERBY {
    /**Declaring local variables for storing database credentials*/
    //private static final String DB_URL = "jdbc:derby://localhost:1527/RPL_DB";
    /*"jdbc:derby:C:/Users/Aparna/.netbeans/7.0/derby/ YourDatabaseName"*/
    /*jdbc:derby:C:/RPL_DB*/
    private static final String DB_URL = "jdbc:derby:./bin/db/secureapps_db";
    private static final String DB_USER_NAME = "secureapps_admin";
    private static final String DB_PASSWORD = "password";
    
    /*declaring sql connection*/
    private static Connection connection;
    
    static
    {
        try 
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        } catch (ClassNotFoundException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
    }
    
    public Connection getConnection()
    {
        return connection;
    }
}

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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author Muhammad Swalah
 */
public class DB_Handler 
{
    public boolean initDB()
    {
        
        boolean status = true;
        try {
            if(!db.validateTable("CLASSES"))
            {
                db.createTable("CLASSES", "CLASS VARCHAR(10) PRIMARY KEY,STREGTH INTEGER");
            }
            if(!db.validateTable("VOTEBANK"))
            {
                db.createTable("VOTEBANK", "BAL_SLNO INTEGER,NAME VARCHAR(20),CLASS VARCHAR(10),VOTE INTEGER");  
            }
            if(!db.validateTable("POLSTATUS"))
            {
                db.createTable("POLSTATUS", "CLASS VARCHAR(10) PRIMARY KEY,POLCOUNT INTEGER,ISCOMPLETED BOOLEAN");
            }
            return status;
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
            status = false;
        }
        return status;
    }
    public boolean resetDB()
    {
        boolean status = true;
        try {
            if(db.validateTable("CLASSES"))
            {
                db.dropTable("CLASSES");
            }
            if(db.validateTable("VOTEBANK"))
            {
                db.dropTable("VOTEBANK");  
            }
            if(db.validateTable("POLSTATUS"))
            {
                db.dropTable("POLSTATUS");
            }
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
            status = false;
        }
        return status;
    }
    DB db = new DB();
    public static void main(String[] args) {
        DB db =new DB();
        System.out.println(db.insertTable("POLSTATUS", "'CS6',1,TRUE"));
        System.out.println(db.getValue("ISCOMPLETED", "POLSTATUS", "CLASS='CS6'"));
    }
}
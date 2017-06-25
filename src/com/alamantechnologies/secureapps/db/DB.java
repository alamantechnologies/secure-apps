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
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
/**
 *
 * @author MUHAMMAD SWALAH A A
 * last edited:2016-JUN-12 :: 06:36 PM
 */
public class DB extends DatabaseConnection_DERBY
{
    private final String _k="6Iv0p2=3tI9SXqRDyL!Z0oS[F2x=/h6VXF|r4cb5H3k1VXU81r3J6O1xQox40b0";
    private final String _k_="Kq5yeKBJ.*7$}4E|0HMn7`4Qh.,uOu1Gt4u.U%g=8N4A+]3{j&wq0.sU6C|tzSe";
    private String dbUrl;
    private String dbUser;
    private String dbPass;
    private String value;
    
    public String getUrl()
    {
        return dbUrl;
    }
    public String getUserName()
    {
        return dbUser;
    }
    public String getPass()
    {
        return dbPass;
    }
    
    
    //////////////////////////////////////////////////////////////////////////
    /*for reading dbUrl,dbUserName,dbPass from dbConfig file*/
    
   
    
//    public void readDB()
//    {
//        try {
//            Config con = new Config();
//            CryptoUtil cry = new CryptoUtil();
//            
//            dbUrl = con.getPro(cry.encrypt(_k_, "dbUrl"),"bin/dbConfig.exe");
//            dbUser = con.getPro(cry.encrypt(_k_, "dbUser"),"bin/dbConfig.exe");
//            dbPass = con.getPro(cry.encrypt(_k_, "dbPass"),"bin/dbConfig.exe");
//            
//            dbUrl = cry.decrypt(_k, dbUrl);
//            dbUser = cry.decrypt(_k, dbUser);
//            dbPass = cry.decrypt(_k, dbPass);
//            
//            
//        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) 
//        {
//            LogManager.logErr(ex);
//            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
//        } catch (UnsupportedEncodingException ex) 
//        {
//            LogManager.logErr(ex);
//            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
//        } catch (IOException ex) 
//        {
//            LogManager.logErr(ex);
//            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
//        }
//        
//    }
    
    /**for creating a table
     * @param tableName
     * @param columnNames */
    public boolean createTable(String tableName, String columnNames)
    {
        int result = -1;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultset;
            result = statement.executeUpdate("CREATE TABLE "+tableName+" ("+columnNames+")");
            
            statement.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        if(result>-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**for inserting values into a table
     * @param tableName
     * @param values
     * @return boolean*/
    public boolean insertTable(String tableName, String values)
    {
        int result = -1;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            result=statement.executeUpdate("INSERT INTO "+tableName+" VALUES ("+values+")");
            
            statement.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        if(result>-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**for inserting values into a table into specified columns
     * @param tableName
     * @param columnNames
     * @param values
     * @return  boolean*/
    public boolean insertTable(String tableName, String columnNames, String values)
    {
        int result = -1;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            result=statement.executeUpdate("INSERT INTO "+tableName+" ("+columnNames+") VALUES ("+values+")");
            
            statement.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        if(result>-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**for updating value of a specific column in a specific row
     * @param tableName
     * @param column1
     * @param value1
     * @param column2
     * @param value2
     * @return boolean */
    public boolean updateTable(String tableName, String column1, String value1, String column2, String value2)
    {
        int result = -1;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            result=statement.executeUpdate("UPDATE "+tableName+" SET "+column1+"="+value1+" WHERE "+column2+"="+value2+"");
            
            statement.close();
        } catch (SQLException ex) {
           LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        if(result>-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**for updating any no of column in one row of a dbTable
     * @param tableName
     * @param set
     * @param whereC
     * @param whereV
     * @return  boolean*/
    public boolean updateTable(String tableName,String set,String whereC,String whereV)
    {
        int result = -1;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            result=statement.executeUpdate("UPDATE "+tableName+" SET "+set+" WHERE "+whereC+"="+whereV+"");
            
            statement.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        if(result>-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**for updating any no of column in one row of a dbTable with any no of conditions
     * @param tableName
     * @param set
     * @param whereClause
     * @return  boolean*/
    public boolean updateTable(String tableName,String set,String whereClause)
    {
        int result = -1;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            result=statement.executeUpdate("UPDATE "+tableName+" SET "+set+" WHERE "+whereClause);
            
            statement.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        if(result>-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /** for deleting  a specific entire row in a table
     * @param tableName
     * @param columnName
     * @param condition
     * @return  boolean*/
    public boolean deleteRecord(String tableName, String columnName, String condition)
    {
        int result = -1;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            result= statement.executeUpdate("DELETE FROM "+tableName+" WHERE "+columnName+"="+condition);
            
            statement.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        if(result>-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /** for deleting  a specific entire row in a table
     * @param tableName
     * @param condition
     * @return  boolean*/
    public boolean deleteRecord(String tableName,String condition)
    {
        int result = -1;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            result = statement.executeUpdate("DELETE FROM "+tableName+" WHERE "+condition);
            
            statement.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        if(result>-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**for deleting or dropping an entire table
     * @param tableName
     * @return  boolean*/
    public boolean dropTable(String tableName)
    {
        int result = -1;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            result=statement.executeUpdate("DROP TABLE "+tableName);
            
            statement.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        if(result>-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**for retrieving the value of a specific column in a specific row
     * @param column_name
     * @param table
     * @param column
     * @param clause
     * @return  value*/
    public String getValue(String column_name,String table,String column,String clause)
    {
        try {
            Connection connection;
            Statement statement;
            connection=getConnection();
            statement = connection.createStatement();
            ResultSet resultset;
            resultset=statement.executeQuery("SELECT "+column_name+" FROM "+table+" WHERE "+column+"="+clause+"");
            
            while(resultset.next())
            {
                
                value=resultset.getString(column_name);
                
            }
            
            statement.close();
            resultset.close();
            
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        return value;
    }
    
    /**for retrieving the value of a specific column in a specific row by checking more than one conditions.
     * @param column_name
     * @param table
     * @param whereClauses
     * @return  value*/
    public String getValue(String column_name,String table,String whereClauses)
    {
        
        try {
            Connection connection;
            Statement statement;
            connection=getConnection();
            statement = connection.createStatement();
            ResultSet resultset;
            resultset=statement.executeQuery("SELECT "+column_name+" FROM "+table+" WHERE "+whereClauses);
            
            while(resultset.next())
            {
                
                value=resultset.getString(column_name);
                
            }
            
            statement.close();
            resultset.close();
            
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        return value;
    }
    
    /**for retrieving the last value of a specific column
     * @param tableName
     * @param columnName
     * @return  last value*/
    public String getLastValue(String tableName,String columnName)
    {
        //String value = null;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultset;
            resultset = statement.executeQuery("SELECT * FROM "+tableName);
            while(resultset.next())
            {
                value = resultset.getString(columnName);
            }
            
            statement.close();
            resultset.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        return value;
    }
    
    /**for retrieving the last value of a specific column which meats the provided
    set of conditions.
     * @param tableName
     * @param columnName
     * @param condition
     * @param sortBase
     * @return  last value*/
    public String getLastValue(String tableName,String columnName,String condition,String sortBase)
    {
        //String value = null;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultset;
            resultset = statement.executeQuery("SELECT * FROM "+tableName+" WHERE "+condition+" ORDER BY "+sortBase);
            while(resultset.next())
            {
                value = resultset.getString(columnName);
            }
            
            statement.close();
            resultset.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        return value;
    }
    
    
    
    
    /**for checking whether the table is available or not
     * @param tableName
     * @return valid or not 
     * @throws java.sql.SQLException */
    public boolean validateTable(String tableName) throws SQLException
    {
        Connection connection;
        Statement statement;
        connection = getConnection();
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet resultset = dbm.getTables(null, null,tableName, null);
        if(resultset.next())
        {
            
            
            resultset.close();
            return true;
        }
        else
        {
            
            
            resultset.close();
            return false;
        }
    }
    
    /**for checking the availability of a particular record in DB table
     * @param tableName
     * @param columnName
     * @param value
     * @return avail or not
     * @throws java.sql.SQLException */
    public boolean checkRecord(String tableName,String columnName,String value) throws SQLException
    {
        Connection connection;
        Statement statement;
        connection = getConnection();
        statement = connection.createStatement();
        ResultSet resultset; 
        resultset=statement.executeQuery("select count(*) from "+tableName+" where "+columnName+"="+value);
        resultset.next();
        if ( resultset.getInt(1) == 0) 
        {
            
            statement.close();
            resultset.close();
            return false;
        } 
        else 
        {
            
            statement.close();
            resultset.close();
            return true;
        }
    }
    
    /**for checking the availability of a particular record
     * in DB table  BY CHECKING MORE THAN ONE WHERE CLAUSE
     * @param tableName
     * @param whereClause
     * @return  avail or not*/
    public boolean checkRecord(String tableName,String whereClause)
    {
        boolean status;
        status = false;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultset;
            resultset=statement.executeQuery("select count(*) from "+tableName+" where "+whereClause);
            resultset.next();
            if ( resultset.getInt(1) == 0)
            {
                
                statement.close();
                resultset.close();
                status = false;
            }
            else
            {
                
                statement.close();
                resultset.close();
                status = true;
                //System.out.println("exists");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
            LogManager.logErr(ex);
        }
        return status;
    }
    
    /**For filling a String array with values of a specific Column in a specific Table.
     * @param array
     * @param tableName
     * @param columnLabel */
    public void fillArray(String array[],String tableName,String columnLabel)
    {
        try {
            int j=0;
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultset;
            resultset = statement.executeQuery("SELECT * FROM "+tableName);
            while(resultset.next())
            {
                array[j] = resultset.getString(columnLabel);
                j++;
            }
            
            statement.close();
            resultset.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
    }
    
    /**For filling a String array with values of a specific Column in a specific Table.
     * @param a
     * @param tableName
     * @param columnLabel
     * @param column
     * @param value */
    public void fillArray(String a[],String tableName,String columnLabel,String column,String value)
    {
        try {
            int j=0;
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultset;
            resultset = statement.executeQuery("SELECT * FROM "+tableName+" WHERE "+column+"="+value);
            while(resultset.next())
            {
                a[j] = resultset.getString(columnLabel);
                j++;
            }
            
            statement.close();
            resultset.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
    }
    
    /**For filling a String array with values of a specific Column
     * in a specific Table with a condition.
     * @param a
     * @param tableName
     * @param columnLabel
     * @param condition */
    public void fillArray(String a[],String tableName,String columnLabel,String condition)
    {
        String monthNames[] = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL","AUG", "SEP", "OCT", "NOV", "DEC"};
        try {
            int j=0;
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultset;
            resultset = statement.executeQuery("SELECT * FROM "+tableName+" WHERE "+condition);
            while(resultset.next())
            {
                a[j] = resultset.getString(columnLabel);
                a[j] = a[j].substring(8,10)+a[j].substring(4, 5)+monthNames[Integer.parseInt(a[j].substring(5, 7))-1]+"-"+a[j].substring(0, 4);
                j++;
            }
            
            statement.close();
            resultset.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
    }
    
    /*retrieving no of rows in a table*/
    public int getRowCount(String tableName)
    {
        int i=0;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultset;
            resultset = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM "+tableName);
            while(resultset.next())
            {
                i = resultset.getInt("COUNT");
            }
            
            statement.close();
            resultset.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        return i;
    }
    
    /**To retrieve the no of rows in a given table with condition
     * @param tableName
     * @param condition
     * @return  rowCount*/
    public int getRowCount(String tableName,String condition)
    {
        int i=0;
        try {
            Connection connection;
            Statement statement;
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultset;
            resultset = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM "+tableName+" WHERE "+condition);
            while(resultset.next())
            {
                i = resultset.getInt("COUNT");
            }
            
            statement.close();
            resultset.close();
        } catch (SQLException ex) {
            LogManager.logErr(ex);
            JOptionPane.showMessageDialog(null, "err:#"+ex.hashCode(), "Error", ERROR_MESSAGE, null);
        }
        return i;
    }
    
    
    
    public static void main(String[] args) throws SQLException {
        DB ob = new DB();
        String tableName = "testError";
        System.out.println(ob.validateTable(tableName));
    }
}
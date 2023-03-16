/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ch.teko.pascal.dbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Pasca
 */
public class Demo02 {
    
    public static final String URL = "jdbc:postgresql://pg14.elad.ch:54324/ltin21ta"; //NOI18N
    
    public void connectDB() throws SQLException{
        try (Connection conn = DriverManager.getConnection(URL,"vonfluep","ybg9581"); 
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT * FROM Products WHERE unitprice >= ?")){ //Pro wert ein ?
            
            stmt.setDouble(1, 10.0);
            
            try (ResultSet rs = stmt.executeQuery()) {            
                while (rs.next()) {
                    System.out.printf(
                            "%5.2f %s\n",
                            rs.getDouble("unitprice"),
                            rs.getString("productname"));
                }
            }
        }
    }
    
    public static void main(String args[])  //static method  
    {  
        System.out.println("Hello World");
        Demo02 test = new Demo02();
        try {
            test.connectDB();
        } catch (SQLException ex) {
            Logger.getLogger(Demo02.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ch.teko.pascal.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Pasca
 */
public class MainModel extends AbstractTableModel implements AutoCloseable{

    private ResultSet rs = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;

    public final String SQL = "SELECT * FROM MVC";

    private String URL = "jdbc:postgresql://pg14.elad.ch:54324/ltin21ta [vonfluep on vonfluep]";
    private String PASW = "ybg9581";
    private String USER = "vonfluep";

    public void open() throws SQLException{
        conn = DriverManager.getConnection(URL, USER, PASW);
        stmt = conn.prepareCall(SQL);
    }

    @Override
    public int getRowCount() {
        return 12;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return String.format("%d / %d", 2,3);
    }

    @Override
    public void close() throws Exception {
        if(conn != null && !conn.isClosed()){
            this.conn.close();
        }
    }

}

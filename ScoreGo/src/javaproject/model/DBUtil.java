
package javaproject.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static jdk.nashorn.internal.runtime.Debug.id;


public class DBUtil {
    
	private Connection conn;
	
	public Connection getConexion() {
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			this.conn = DriverManager.getConnection("jdbc:mysql://scorego.ddns.net/scor_godb", "scor_godbusr", "1234567");
                        //this.conn = DriverManager.getConnection("jdbc:mysql://localhost/scor_godb", "root", "");
			return this.conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void cerrarConexion() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
        
        
   
}

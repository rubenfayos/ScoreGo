
package javaproject.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static jdk.nashorn.internal.runtime.Debug.id;


public class DBUtil {
        //Crear una nueva conexión
	private Connection conn;
	
	public Connection getConexion() {
		//Método para conectarse a la base de datos
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
	//Cerrar la conexión
	public void cerrarConexion() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
        
        
   
}

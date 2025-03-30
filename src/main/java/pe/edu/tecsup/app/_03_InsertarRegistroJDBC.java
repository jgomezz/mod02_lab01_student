package pe.edu.tecsup.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static pe.edu.tecsup.app.Parametros.*;

public class _03_InsertarRegistroJDBC {

	public static void main(String[] args) {
		
		try {
			// 1. Cargar el driver en memoria
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Conectarme a la BD
			Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			// Preparar la sentencia SQL
			String sql = "INSERT INTO categorias (nombre,descripcion,orden) VALUES (?,?,?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Preparar los datos a Ingresar
			stmt.setString(1, "Tarjetas de Video");              // corresponde al nombre
			stmt.setString(2, "Tarjetas de Video NVIDIA"); // corresponde a la descripcion
			stmt.setInt(3, 4);                            // corresponde al orden
			

			// Ejecutar la insercion
			int estado = stmt.executeUpdate(); 
			
			// Analizar el resultado
			if (estado != 1)
				throw new SQLException("No se pudo insertar");
			
			// /*
			// Obtener el ultimo id
			int id = 0;
			sql = "SELECT last_insert_id()"; 
			stmt = con.prepareStatement(sql); 
			java.sql.ResultSet rs = stmt.executeQuery(); 
			if (rs.next()) 
				id = rs.getInt(1);
			System.out.println("Se inserto el registro de categoria con ID = " + id);
			
			
			// Cerrar conexiones
			rs.close(); 
			//*/
			
			stmt.close();
			
			con.close();
			
			System.out.println("Registro creado");
			
		} catch (Exception e) {
			e.printStackTrace();
			//System.err.println(e.getMessage());
		}
	}

}
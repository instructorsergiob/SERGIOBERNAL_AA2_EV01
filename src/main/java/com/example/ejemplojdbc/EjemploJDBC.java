
//Aquií estamos apuntando a un paquete en especifico
package com.example.ejemplojdbc;


//Estos son las importaciones de nuestros archivos internos
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//Esta es la clase principal
public class EjemploJDBC {
    
    //Metodo para conectarme al driver JDBC
    protected static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
    
        //Variables de entorno
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "ejemplo";
        String dbUsername = "root";
        String dbPassword = "";
        
        
        //Ejecuto el driver JDBC
       Class.forName(dbDriver);
        
        //retorno la instancia a JDBC
        return DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
    }

    //Esta es la clase principal para ejecutar JAVA
    public static void main(String[] args){
        
        //Crear la variable de concatenacion
        int idToDelete = 10;
        
        //Usamos el bloque Try-catch para capturar errores
        try(
             Connection con = initializeDatabase();
             PreparedStatement   st = con.prepareStatement("DELETE FROM aprendiz WHERE idAprendiz = ?") ;
            ){
            
            //Encapsulamos los datos que vienen por la variable
            st.setInt(1,idToDelete);
            
            //Crear la variable que va a elimiar los datos
            int rowsAffected = st.executeUpdate();
            
            //Valida si se eliminaron los datos
            if(rowsAffected > 0){
                System.out.println("Registro eliminado");
            }else{
            
                System.out.println("No se encontró dicho ID");
            }
            
           
        
        }catch(ClassNotFoundException | NumberFormatException | SQLException e){
            
            System.out.println("No se encuentra el ID en la BD:"+ e.getMessage());
        }
       
    }
    
}

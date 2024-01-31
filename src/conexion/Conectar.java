/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.*;

public class Conectar {

    public static Connection c;
    private static Connection c2;
    public static Connection server_con;
    public static String MENSAJE = "";
    private String host = "localhost";
    private String dbname = "bdticket";
    private String passwd = "informatica";

    public java.sql.Connection conectaServidor() {
        try {
            MENSAJE = "";
            String url = "jdbc:postgresql://" + host;
            Class.forName("org.postgresql.Driver");
            server_con = java.sql.DriverManager.getConnection(url, "postgres", passwd);
        } catch (Exception ex) {
            MENSAJE = ex.getMessage();
        }
        return server_con;
    }

    public void creaBD(String stm) {
        try {
            MENSAJE = "";
            c2 = server_con;
            Statement s = c2.createStatement();
            s.executeUpdate(stm);
            c2.close();
        } catch (Exception ex) {
            MENSAJE = ex.getMessage();
        }
    }

    public java.sql.Connection conectaBD() {
        String url = "jdbc:postgresql://" + host + "/" + dbname;
        MENSAJE = "";
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, "postgres", passwd);

        } catch (Exception ex) {
            MENSAJE = ex.getMessage();
        }
        return c;
    }

    public java.sql.ResultSet consulta(String consul) {
        java.sql.ResultSet r = null;
        MENSAJE = "";
        try {
            System.out.println("Conexión: " + conexion.Conectar.c); // Verifica si es null
            java.sql.Statement stm = c.createStatement();
            r = stm.executeQuery(consul);
        } catch (SQLException e) {
            MENSAJE = e.getMessage();
        }
        return r;
    }

    public void ejecutar(String stm) {
        try {
            MENSAJE = "";
            Statement s = c.createStatement();
            s.executeUpdate(stm);
        } catch (Exception e) {
            MENSAJE = e.getMessage();
        }
    }

}

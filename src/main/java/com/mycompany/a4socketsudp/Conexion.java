package com.mycompany.a4socketsudp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Danielaa
 */
public class Conexion {

    private static Conexion conexionInstancia = null;
    private Connection connection = null;
    private final String URL = "jdbc:postgresql://localhost:5432/sockets";
    private final String USUARIO = "postgres";
    private final String CONTRASENIA = "password";

    private Conexion() {
        try {
            connection = DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
            System.out.println("Conexion establecida");
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static Conexion getSingletonInstancia() {
        if (conexionInstancia == null) {
            conexionInstancia = new Conexion();
        }

        return conexionInstancia;
    }

    public Connection getConnection() {
        return connection;
    }
}

package org.example;

import java.io.*;
import java.sql.*;

public class CrearFuncionProcedimiento {
    public static void main(String[] args) {
        // Configuración de la conexión
        String url = "jdbc:mysql://localhost:3306/practica?allowMultiQueries=true";
        String user = "root";
        String password = "practica";

        // Lee el archivo que contiene el código SQL
        StringBuilder sqlScript = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("crear_funciones.sql"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sqlScript.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Imprime el SQL para ver qué se está enviando
        System.out.println("SQL a ejecutar:");
        System.out.println(sqlScript.toString()); // Imprime el SQL

        // Ejecuta el script SQL
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            // Ejecutar el script de creación sin DELIMITER ni SET GLOBAL
            statement.execute(sqlScript.toString());
            System.out.println("Funciones y procedimientos creados exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

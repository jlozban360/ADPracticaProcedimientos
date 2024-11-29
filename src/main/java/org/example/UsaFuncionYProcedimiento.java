package org.example;

import java.sql.*;
import java.math.BigDecimal;

public class UsaFuncionYProcedimiento {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/practica";
        String user = "root";
        String password = "practica";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT dept_no, obtener_salario_maximo(dept_no) AS salario_maximo FROM departamentos";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    int deptNo = rs.getInt("dept_no");
                    double salarioMax = rs.getDouble("salario_maximo");
                    System.out.println("Departamento " + deptNo + ": Salario máximo = " + salarioMax);
                }
            }

            String procedimiento = "{CALL obtener_info_departamento(?, ?, ?)}";
            try (CallableStatement callableStatement = connection.prepareCall(procedimiento)) {
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT dept_no FROM departamentos")) {

                    while (rs.next()) {
                        int deptNo = rs.getInt("dept_no");
                        callableStatement.setInt(1, deptNo);
                        callableStatement.registerOutParameter(2, Types.INTEGER);
                        callableStatement.registerOutParameter(3, Types.DECIMAL);

                        callableStatement.execute();

                        int numEmpleados = callableStatement.getInt(2);
                        BigDecimal salarioMedio = callableStatement.getBigDecimal(3);

                        System.out.println("Departamento " + deptNo + ": Empleados = " + numEmpleados + ", Salario medio = " + salarioMedio);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

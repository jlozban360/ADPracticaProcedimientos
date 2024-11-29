DROP PROCEDURE IF EXISTS obtener_info_departamento;

CREATE PROCEDURE obtener_info_departamento(
    IN dept_no INT,
    OUT num_empleados INT,
    OUT salario_medio DECIMAL(10,2)
)
BEGIN
SELECT COUNT(*) INTO num_empleados
FROM empleados
WHERE dept_no = dept_no;

SELECT AVG(salario) INTO salario_medio
FROM empleados
WHERE dept_no = dept_no;
END;

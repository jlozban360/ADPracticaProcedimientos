DROP PROCEDURE IF EXISTS obtener_info_departamento;
DROP FUNCTION IF EXISTS obtener_salario_maximo;

CREATE FUNCTION obtener_salario_maximo(dept_no_param INT)
    RETURNS DECIMAL(10,2)
    DETERMINISTIC
BEGIN
    DECLARE salario_maximo DECIMAL(10,2);

SELECT MAX(salario) INTO salario_maximo
FROM empleados
WHERE dept_no = dept_no;

RETURN salario_maximo;
END;

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

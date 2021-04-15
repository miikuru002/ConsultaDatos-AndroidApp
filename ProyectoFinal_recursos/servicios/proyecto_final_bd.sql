--CREATE DATABASE IF NOT EXISTS `proyecto_final_bd` DEFAULT CHARACTER SET latin1 COLLATE LATIN1_SWEDISH_CI;
--USE `proyecto_final_bd`;
--USE `bii8tqslygoiq2ppkqvl`;
----------------------------------------------------------Activar Base de datos
CREATE TABLE `usuarios` (
  `dni` VARCHAR(8) PRIMARY KEY NOT NULL,
  `persona_empresa` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `password` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=LATIN1;
----------------------------------------------------------Estructura de tabla para la tabla `usuario`
INSERT INTO `usuarios` (`dni`, `nombres`, `apellidos`, `password`) VALUES
('12345678', 'Nombre1', 'Apellido1', '123abc'),
('87654321', 'Nombre2', 'Apellido2', '321abc');
----------------------------------------------------------Volcado de datos para la tabla `usuario`
COMMIT
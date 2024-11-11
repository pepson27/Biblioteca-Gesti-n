-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-04-2024 a las 17:08:34
-- Versión del servidor: 8.0.34
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca_pac`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lector`
--

CREATE TABLE `lector` (
  `Id` int NOT NULL,
  `Nombre` varchar(99) NOT NULL,
  `Apellido` varchar(99) NOT NULL,
  `Email` varchar(99) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `lector`
--

INSERT INTO `lector` (`Id`, `Nombre`, `Apellido`, `Email`) VALUES
(1, 'Raúl', 'Smith', 'raulsmith@yahoo.com.es'),
(2, 'Jose', 'Fernandez', 'josefer48@hotmail.com'),
(3, 'Paula', 'Rodriguez', 'rodri57@gmail.com'),
(4, 'Felipe', 'Gonzalez', 'philips@gmail.com'),
(5, 'Fran', 'Martinez', 'franchise@gmail.com'),
(6, 'María', 'James', 'texmex41@gmail.com'),
(7, 'Sofía', 'Alonso', 'alonso37@gmail.com'),
(8, 'Lionel', 'Messi', 'messi10@yahoo.com.ar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `Id` int NOT NULL,
  `Titulo` varchar(100) NOT NULL,
  `Autor` varchar(100) NOT NULL,
  `AñoPublicacion` int NOT NULL,
  `Disponible` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`Id`, `Titulo`, `Autor`, `AñoPublicacion`, `Disponible`) VALUES
(1, 'El Quijote', 'Miguel de Cervantes', 1605, 0),
(2, 'Tom Sawyer', 'Arthur Connan Doyle', 2008, 0),
(3, 'Harry Potter y la camara de los secretos', 'JK Rowling', 1997, 1),
(4, 'Harry Potter y el prisionero de Azkaban\r\n', 'JK Rowling', 2004, 0),
(5, 'Harry Potter y el cáliz del fuego', 'JK Rowling', 2001, 1),
(6, 'Harry Potter y la órden del fénix', 'JK Rowling', 2001, 1),
(7, 'Harry Potter Parte 6', 'JK Rowling', 2001, 1),
(8, 'Harry Potter y las reliquias de la muerte', 'JK Rowling', 2001, 1),
(9, 'Harry Potter y las reliquias de la muerte parte 2', 'JK Rowling', 2004, 1),
(10, 'El señor de los anillos 1', 'J.R.R. Tolkien', 1954, 0),
(11, 'El señor de los anillos 2', 'J.R.R. Tolkien', 1954, 1),
(12, 'El señor de los anillos 3', 'J.R.R. Tolkien', 1954, 1),
(13, 'El señor de los anillos 4', 'J.R.R. Tolkien', 1954, 1),
(14, 'El señor de los anillos 5', 'J.R.R. Tolkien', 1954, 1),
(15, 'El señor de los anillos 6', 'J.R.R. Tolkien', 1954, 1),
(16, 'Maze Runner', 'James Dashner', 2009, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamo`
--

CREATE TABLE `prestamo` (
  `Id` int NOT NULL,
  `Libro` int NOT NULL,
  `Lector` int NOT NULL,
  `FechaPrestamo` date NOT NULL,
  `FechaDevolucion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `prestamo`
--

INSERT INTO `prestamo` (`Id`, `Libro`, `Lector`, `FechaPrestamo`, `FechaDevolucion`) VALUES
(1, 1, 1, '2024-02-15', '2035-06-14'),
(2, 2, 2, '2024-02-15', '2035-06-14'),
(3, 13, 2, '2024-04-14', '2035-04-21'),
(4, 3, 2, '2024-05-27', '2035-07-20'),
(5, 4, 2, '2024-04-20', '2035-07-20'),
(6, 10, 8, '2024-04-15', '2035-07-17');

--
-- Disparadores `prestamo`
--
DELIMITER $$
CREATE TRIGGER `libro_disponible_before_delete_prestamo` BEFORE DELETE ON `prestamo` FOR EACH ROW BEGIN
    DECLARE idLibro_fk INT;

    -- Obtener el idLibro_fk de la fila a ser eliminada
    SET idLibro_fk = OLD.Libro;

    -- Actualizar la columna disponible en la tabla libro
    UPDATE libro
    SET disponible = TRUE
    WHERE Id = idLibro_fk;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_libro_disponible` AFTER UPDATE ON `prestamo` FOR EACH ROW BEGIN
    IF NEW.FechaDevolucion < NEW.FechaPrestamo THEN
        UPDATE Libro
        SET disponible = TRUE
        WHERE id = NEW.Id;
    END IF;
END
$$
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `lector`
--
ALTER TABLE `lector`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Libro` (`Libro`,`Lector`),
  ADD KEY `prestamo_ibfk_2` (`Lector`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `lector`
--
ALTER TABLE `lector`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `libro`
--
ALTER TABLE `libro`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `prestamo`
--
ALTER TABLE `prestamo`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD CONSTRAINT `prestamo_ibfk_1` FOREIGN KEY (`Libro`) REFERENCES `libro` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prestamo_ibfk_2` FOREIGN KEY (`Lector`) REFERENCES `lector` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

DELIMITER $$
--
-- Eventos
--
CREATE DEFINER=`root`@`localhost` EVENT `check_due_date` ON SCHEDULE EVERY 1 DAY STARTS '2024-04-22 08:29:17' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    UPDATE Libro
    SET disponible = TRUE
    WHERE id IN (
        SELECT p.libro
        FROM Préstamo p
        INNER JOIN Libro l ON p.libro = l.id
        WHERE p.fechaDevolucion < CURDATE() 
        AND l.disponible = FALSE
    );
END$$

CREATE DEFINER=`root`@`localhost` EVENT `check_due_date2` ON SCHEDULE EVERY 1 DAY STARTS '2024-04-22 08:30:27' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    UPDATE Libro
    SET disponible = FALSE
    WHERE id IN (
        SELECT p.libro
        FROM Préstamo p
        INNER JOIN Libro l ON p.libro = l.id
        WHERE p.fechaDevolucion > CURDATE() 
        AND l.disponible = TRUE
    );
END$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.9.2deb1
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:3306
-- Время создания: Фев 24 2020 г., 10:11
-- Версия сервера: 10.3.22-MariaDB-1
-- Версия PHP: 7.3.12-1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `shop_application`
--

-- --------------------------------------------------------

--
-- Структура таблицы `products`
--

CREATE TABLE `products` (
  `productId` bigint(20) NOT NULL,
  `productName` varchar(100) NOT NULL,
  `productRegularPrice` decimal(5,2) NOT NULL,
  `productCategory` enum('ALCOHOL','BREAD','FISH','FRUITS','MEAT','MILK','SOFT_DRINKS','SWEETS','VEGETABLES') NOT NULL,
  `productDiscount` decimal(5,2) NOT NULL,
  `productDescription` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `products`
--

INSERT INTO `products` (`productId`, `productName`, `productRegularPrice`, `productCategory`, `productDiscount`, `productDescription`) VALUES
(1, 'Milk', '25.00', 'MILK', '0.00', 'Good milk from Latvia'),
(3, 'Pork', '125.00', 'MEAT', '50.00', 'Good pork from Porya'),
(7, 'Tess', '25.00', 'SOFT_DRINKS', '5.00', 'Tea with berries'),
(8, 'Beef', '50.00', 'MEAT', '0.00', NULL);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`productId`),
  ADD UNIQUE KEY `productName` (`productName`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `products`
--
ALTER TABLE `products`
  MODIFY `productId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

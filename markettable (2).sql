-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 03, 2021 at 07:53 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `markettable`
--

-- --------------------------------------------------------

--
-- Table structure for table `buy_order`
--

CREATE TABLE `buy_order` (
  `id_Buy` int(11) NOT NULL,
  `item_Name` varchar(255) NOT NULL,
  `item_Desc` varchar(255) DEFAULT NULL,
  `item_Price` int(11) DEFAULT NULL,
  `id` int(11) DEFAULT NULL,
  `post_Date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sell_order`
--

CREATE TABLE `sell_order` (
  `id_Sell` int(11) NOT NULL,
  `item_Name` varchar(255) NOT NULL,
  `item_Desc` varchar(255) DEFAULT NULL,
  `item_Price` int(11) DEFAULT NULL,
  `id` int(11) DEFAULT NULL,
  `post_Date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sold`
--

CREATE TABLE `sold` (
  `id_Sold` int(11) NOT NULL,
  `id_Sell` int(11) DEFAULT NULL,
  `item_Name` varchar(255) NOT NULL,
  `item_Price` int(11) DEFAULT NULL,
  `sold_Date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(230) NOT NULL,
  `password` text NOT NULL,
  `phone` varchar(32) NOT NULL,
  `city` varchar(15) NOT NULL,
  `address` varchar(230) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `email` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buy_order`
--
ALTER TABLE `buy_order`
  ADD PRIMARY KEY (`id_Buy`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `sell_order`
--
ALTER TABLE `sell_order`
  ADD PRIMARY KEY (`id_Sell`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `sold`
--
ALTER TABLE `sold`
  ADD PRIMARY KEY (`id_Sold`),
  ADD KEY `id_Sell` (`id_Sell`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buy_order`
--
ALTER TABLE `buy_order`
  MODIFY `id_Buy` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sell_order`
--
ALTER TABLE `sell_order`
  MODIFY `id_Sell` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sold`
--
ALTER TABLE `sold`
  MODIFY `id_Sold` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `buy_order`
--
ALTER TABLE `buy_order`
  ADD CONSTRAINT `buy_order_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

--
-- Constraints for table `sell_order`
--
ALTER TABLE `sell_order`
  ADD CONSTRAINT `sell_order_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

--
-- Constraints for table `sold`
--
ALTER TABLE `sold`
  ADD CONSTRAINT `sold_ibfk_1` FOREIGN KEY (`id_Sell`) REFERENCES `sell_order` (`id_Sell`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

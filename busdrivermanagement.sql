-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2024 at 12:26 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `busdrivermanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `bangphancong`
--

CREATE TABLE `bangphancong` (
  `laiXe_id` int(11) NOT NULL,
  `tuyen_id` int(11) NOT NULL,
  `soLuot` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `bangphancong`
--

INSERT INTO `bangphancong` (`laiXe_id`, `tuyen_id`, `soLuot`) VALUES
(10002, 100, 3),
(10003, 100, 1),
(10002, 101, 5);

-- --------------------------------------------------------

--
-- Table structure for table `laixe`
--

CREATE TABLE `laixe` (
  `id` int(11) NOT NULL,
  `hoTen` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `diaChi` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SDT` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `trinhDo` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `laixe`
--

INSERT INTO `laixe` (`id`, `hoTen`, `diaChi`, `SDT`, `trinhDo`) VALUES
(10002, 'Lưu Quốc Đạt', 'ninh bình', '123123', 'A'),
(10003, 'phạm ngọc ánh', 'ninh bình', '123123', 'A'),
(10004, 'Lưu Đình Luyện', 'Ninh bình', '1231231', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `tuyen`
--

CREATE TABLE `tuyen` (
  `id` int(11) NOT NULL,
  `khoangCach` decimal(10,2) NOT NULL,
  `soDiemDung` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `tuyen`
--

INSERT INTO `tuyen` (`id`, `khoangCach`, `soDiemDung`) VALUES
(100, 10000.00, 3),
(101, 4000.00, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bangphancong`
--
ALTER TABLE `bangphancong`
  ADD KEY `laiXe_id` (`laiXe_id`),
  ADD KEY `tuyen_id` (`tuyen_id`);

--
-- Indexes for table `laixe`
--
ALTER TABLE `laixe`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tuyen`
--
ALTER TABLE `tuyen`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bangphancong`
--
ALTER TABLE `bangphancong`
  ADD CONSTRAINT `bangphancong_ibfk_1` FOREIGN KEY (`laiXe_id`) REFERENCES `laixe` (`id`),
  ADD CONSTRAINT `bangphancong_ibfk_2` FOREIGN KEY (`tuyen_id`) REFERENCES `tuyen` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

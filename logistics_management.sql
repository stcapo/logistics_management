-- Database: logistics_management
DROP DATABASE IF EXISTS `logistics_management`;
CREATE DATABASE `logistics_management` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `logistics_management`;

-- Table structure for user table
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `passwd` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert test data for user table
INSERT INTO `user` (`username`, `passwd`) VALUES
('管理员', 'admin123'),
('司机1', 'driver123'),
('司机2', 'driver456'),
('经理1', 'manager123'),
('操作员1', 'operator123');

-- Table structure for vehicle table
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `license_plate` varchar(20) NOT NULL,
  `model` varchar(50) NOT NULL,
  `load_capacity` decimal(10,2) NOT NULL COMMENT 'in tons',
  PRIMARY KEY (`id`),
  UNIQUE KEY `license_plate_UNIQUE` (`license_plate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert test data for vehicle table
INSERT INTO `vehicle` (`license_plate`, `model`, `load_capacity`) VALUES
('京A12345', '卡车型号A', 5.00),
('京B67890', '厢式货车B', 2.50),
('京C13579', '卡车型号C', 7.50),
('京D24680', '厢式货车D', 3.00),
('京E35791', '大型卡车E', 10.00);

-- Table structure for order table
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `vehicleid` int(11) DEFAULT NULL,
  `order_status` enum('pending','assigned','in_transit','delivered','canceled') NOT NULL DEFAULT 'pending' COMMENT '待处理,已分配,运输中,已交付,已取消',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_order_user_idx` (`userid`),
  KEY `fk_order_vehicle_idx` (`vehicleid`),
  CONSTRAINT `fk_order_user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_vehicle` FOREIGN KEY (`vehicleid`) REFERENCES `vehicle` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert test data for order table
INSERT INTO `order` (`userid`, `vehicleid`, `order_status`, `creation_time`) VALUES
(1, 1, 'delivered', '2025-03-15 10:00:00'),
(2, 2, 'in_transit', '2025-03-16 11:30:00'),
(3, 3, 'assigned', '2025-03-17 09:15:00'),
(4, 4, 'pending', '2025-03-18 14:45:00'),
(5, 5, 'canceled', '2025-03-19 08:30:00');

-- Table structure for dispatch management table
DROP TABLE IF EXISTS `dispatch_management`;
CREATE TABLE `dispatch_management` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` int(11) NOT NULL,
  `vehicleid` int(11) NOT NULL,
  `driverid` int(11) NOT NULL,
  `task` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dispatch_order_idx` (`orderid`),
  KEY `fk_dispatch_vehicle_idx` (`vehicleid`),
  KEY `fk_dispatch_driver_idx` (`driverid`),
  CONSTRAINT `fk_dispatch_order` FOREIGN KEY (`orderid`) REFERENCES `order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dispatch_vehicle` FOREIGN KEY (`vehicleid`) REFERENCES `vehicle` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dispatch_driver` FOREIGN KEY (`driverid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert test data for dispatch management table
INSERT INTO `dispatch_management` (`orderid`, `vehicleid`, `driverid`, `task`) VALUES
(1, 1, 1, '将货物送至A仓库'),
(2, 2, 2, '运送包裹至B客户'),
(3, 3, 3, '从C供应商处提取物资'),
(4, 4, 4, '将设备送至D现场'),
(5, 5, 5, '运送材料至E工厂');

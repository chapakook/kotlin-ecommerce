DROP TABLE IF EXISTS `points`;

CREATE TABLE `points` (
  `balance` bigint NOT NULL,
  `point_id` bigint NOT NULL AUTO_INCREMENT,
  `update_millis` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`point_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `points` (`point_id`,`user_id`,`balance`,`update_millis`)
VALUES
  (1,1,12535,0),
  (2,2,45241,0),
  (3,3,89384,0),
  (4,4,33802,0),
  (5,5,23614,0),
  (6,6,35945,0),
  (7,7,38867,0),
  (8,8,77292,0),
  (9,9,33227,0),
  (10,10,16841,0);
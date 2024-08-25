-- spring_security.users definition

CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO spring_security.users(id, username, password)VALUES(3, 'lucy', '123');
INSERT INTO spring_security.users(id, username, password)VALUES(4, 'bob', '456');

select * from users u ;

--

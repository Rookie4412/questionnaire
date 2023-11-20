CREATE TABLE IF¡@¢Ü¢Ý¢â¡@¢Ó¢æ¢×¢á¢â¢á¡@`question` (
  `id` int NOT NULL,
  `qn_id` int NOT NULL,
  `q_title` varchar(60) DEFAULT NULL,
  `option_type` varchar(20) DEFAULT NULL,
  `is_necessary` tinyint DEFAULT '0',
  `q_option` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`,`qn_id`)
);

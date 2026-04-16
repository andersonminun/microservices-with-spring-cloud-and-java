CREATE TABLE `book` (
    `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
    `title` longtext,
    `author` longtext,
    `launch_date` datetime(6) NOT NULL,
    `price` decimal(65, 2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `notifications`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `created_at`   datetime(6) DEFAULT NULL,
    `message`      varchar(255) DEFAULT NULL,
    `service_name` varchar(255) DEFAULT NULL,
    `to_email`     varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
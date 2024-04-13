CREATE TABLE `students`
(
    `id`                bigint NOT NULL AUTO_INCREMENT,
    `created_date`      datetime(6) NOT NULL,
    `last_updated_date` datetime(6) DEFAULT NULL,
    `address`           varchar(255) DEFAULT NULL,
    `date_of_birth`     date DEFAULT NULL,
    `gender`            varchar(255) DEFAULT NULL,
    `grade`             varchar(255) DEFAULT NULL,
    `name`              varchar(255) DEFAULT NULL,
    `email`             varchar(255) DEFAULT NULL,
    `phone_number`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
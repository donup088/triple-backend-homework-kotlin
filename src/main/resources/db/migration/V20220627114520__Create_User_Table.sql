DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `user_id`    BIGINT      NOT NULL AUTO_INCREMENT,
    `nickname`   VARCHAR(50) NOT NULL,
    `point`      INT         NOT NULL,
    `created_at` DATETIME    NOT NULL,
    `updated_at` DATETIME DEFAULT NULL,
    PRIMARY KEY (`user_id`)
);
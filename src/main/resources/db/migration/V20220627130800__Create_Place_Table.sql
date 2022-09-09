DROP TABLE IF EXISTS `place`;

CREATE TABLE `place`
(
    `place_id` BIGINT      NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(50) NOT NULL,
    PRIMARY KEY (`place_id`)
);
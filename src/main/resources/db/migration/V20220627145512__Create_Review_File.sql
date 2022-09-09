DROP TABLE IF EXISTS `review_file`;

CREATE TABLE `review_file`
(
    `review_file_id` BIGINT        NOT NULL AUTO_INCREMENT,
    `review_id`      BIGINT        NOT NULL,
    `url`            VARCHAR(2083) NOT NULL,
    `name`           VARCHAR(255)  NOT NULL,
    `extension`      VARCHAR(255)  NOT NULL,
    `size`           BIGINT        NOT NULL,
    `width`          INT,
    `height`         INT,
    `created_at`     DATETIME      NOT NULL,
    `updated_at`     DATETIME DEFAULT NULL,
    PRIMARY KEY (`review_file_id`)
);

CREATE INDEX `review_id_idx` ON `review_file` (`review_id`);
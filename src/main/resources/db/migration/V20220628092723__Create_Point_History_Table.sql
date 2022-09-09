DROP TABLE IF EXISTS `point_history`;

CREATE TABLE `point_history`
(
    `point_history_id` BIGINT      NOT NULL AUTO_INCREMENT,
    `user_id`          BIGINT      NOT NULL,
    `place_id`         BIGINT      NOT NULL,
    `review_id`        BIGINT      NOT NULL,
    `type`             VARCHAR(20) NOT NULL,
    `action`           VARCHAR(20) NOT NULL,
    `point`            INT         NOT NULL,
    `content_exist`    TINYINT(1)  NOT NULL,
    `img_exist`        TINYINT(1)  NOT NULL,
    `created_at`       DATETIME    NOT NULL,
    `updated_at`       DATETIME DEFAULT NULL,
    PRIMARY KEY (`point_history_id`)
);
CREATE INDEX `user_id_idx` ON `point_history` (`user_id`);
CREATE INDEX `place_id_idx` ON `point_history` (`place_id`);
CREATE INDEX `review_id_idx` ON `point_history` (`review_id`);

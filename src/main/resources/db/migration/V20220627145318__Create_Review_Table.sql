DROP TABLE IF EXISTS `review`;

CREATE TABLE `review`
(
    `review_id`  BIGINT   NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT   NOT NULL,
    `place_id`   BIGINT   NOT NULL,
    `content`    TEXT,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME DEFAULT NULL,
    PRIMARY KEY (`review_id`)
);
CREATE INDEX `user_id_idx` ON `review` (`user_id`);
CREATE INDEX `place_id_idx` ON `review` (`place_id`);
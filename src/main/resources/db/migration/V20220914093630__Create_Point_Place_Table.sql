DROP TABLE IF EXISTS `point_history`;

CREATE TABLE `point_place`
(
    `point_place_id`  BIGINT   NOT NULL AUTO_INCREMENT,
    `place_id`        BIGINT   NOT NULL,
    `first_review_id` BIGINT   NOT NULL,
    `version`         BIGINT   NOT NULL,
    `created_at`      DATETIME NOT NULL,
    `updated_at`      DATETIME DEFAULT NULL,
    PRIMARY KEY (`point_place_id`)
);

CREATE INDEX `place_id_idx` ON `point_place` (`place_id`);


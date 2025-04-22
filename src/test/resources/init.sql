DROP TABLE IF EXISTS `points`;

create table `points`
(
    `balance`       bigint not null,
    `point_id`      bigint auto_increment primary key,
    `update_millis` bigint not null,
    `user_id`       bigint not null
);

INSERT INTO points (`point_id`,`user_id`,`balance`,`update_millis`)
VALUES
  (1,1,12535,0),
  (2,2,45241,0),
  (3,3,89384,0),
  (4,4,33802,0),
  (5,5,23614,0),

  (6,6,35945,0),
  (7,7,38867,0),
  (8,8,77292,0),
  (9,9,33227,0),
  (10,10,16841,0);

DROP TABLE IF EXISTS `coupon_events`;

create table `coupon_events`
(
    `current_count`   int     not null,
    `max_count`       int     not null,
    `type`            tinyint not null,
    `coupon_event_id` bigint auto_increment primary key,
    `expiry_millis`   bigint  not null,
    `value`           bigint  not null,
    check (`type` between 0 and 1)
);

INSERT INTO coupon_events (`coupon_event_id`,`max_count`,`expiry_millis`,`value`,`type`,`current_count`)
VALUES
    (1,82166,0,281,1,10),
    (2,58462,0,926,1,7),
    (3,52739,0,431,1,8),
    (4,1659,0,263,1,2),
    (5,70938,0,120,1,5),
    (6,23364,0,567,1,4),
    (7,9849,0,394,0,0),
    (8,93659,0,937,1,1),
    (9,2386,0,994,1,1),
    (10,13873,0,694,0,0);
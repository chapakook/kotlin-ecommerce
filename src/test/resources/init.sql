create table `points`
(
    `balance`       bigint not null,
    `point_id`      bigint auto_increment primary key,
    `update_millis` bigint not null,
    `user_id`       bigint not null,
    `version`       bigint not null
);

INSERT INTO `points` (`point_id`,`user_id`,`balance`,`update_millis`,`version`)
VALUES
  (1,1,12535,0,0),
  (2,2,45241,0,0),
  (3,3,89384,0,0),
  (4,4,33802,0,0),
  (5,5,23614,0,0),
  (6,6,35945,0,0),
  (7,7,38867,0,0),
  (8,8,77292,0,0),
  (9,9,33227,0,0),
  (10,10,16841,0,0);

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

INSERT INTO `coupon_events` (`coupon_event_id`,`max_count`,`expiry_millis`,`value`,`type`,`current_count`)
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

create table `stocks`
(
    `quantity`   int    not null,
    `version`    int    not null,
    `product_id` bigint not null,
    `stock_id`   bigint auto_increment primary key
);


INSERT INTO `stocks` (`stock_id`,`product_id`,`quantity`,`version`)
VALUES
    (1,1,296,0),
    (2,2,902,0),
    (3,3,570,0),
    (4,4,863,0),
    (5,5,434,0),
    (6,6,724,0),
    (7,7,281,0),
    (8,8,283,0),
    (9,9,444,0),
    (10,10,754,0);